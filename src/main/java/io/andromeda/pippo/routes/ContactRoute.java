/*
 * Copyright (C) 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.andromeda.pippo.routes;

import io.andromeda.pippo.routes.fields.Field;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.util.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.Session;
import ro.pippo.core.route.RouteContext;
import ro.pippo.core.route.RouteGroup;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The contact route implementation.
 * @author Alexander Brandt
 */
public class ContactRoute extends RouteGroup {
    /** The logger instance for this class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactRoute.class);
    private static final String TITLE_ID = "title";
    private ContactRouteConfiguration configuration;
    private List<Field> fields;

    public ContactRoute(ContactRouteConfiguration configuration, List<Field> fields, Map<String, Object> defaultContext) {
        super(configuration.getUrl());

        this.fields = fields;

        /* Override the default Simple Java Mail configuration */
        if (configuration.getSimplejavamailConfiguration() != null) {
            ConfigLoader.loadProperties(configuration.getSimplejavamailConfiguration(), false);
        }

        this.configuration = configuration;

        GET(configuration.getUrl(), routeContext -> {
            Session session = routeContext.getRequest().getSession(true);
            routeContext.render(configuration.getTemplate(), defaultContext);
        });

        POST(configuration.getUrl(), routeContext -> {
            Map<String, String> fieldMessages = new TreeMap<>();
            Map<String, Object> context = defaultContext;
            context.put(TITLE_ID, configuration.getFormTitle());

            Session session = routeContext.getSession();
            boolean everythingOk = true;
            for (Field field: fields) {
                String parameter = routeContext.getParameter(field.getName()).toString();
                if (parameter == null) {
                    parameter = "";
                }
                String temp = decode(parameter);
                field.setValue(temp);
                if (field.doesValidate()) {
                    everythingOk = field.validate(fieldMessages) && everythingOk;
                }
            }

            if (!everythingOk) {
                for (Field field: fields) {
                    session.put(field.getName(), field.getValue());
                }
                for (Map.Entry message: fieldMessages.entrySet()) {
                    session.put((String)message.getKey(), getMessage(routeContext, (String)message.getValue()));
                }
                session.put(Constants.SUCCESS_ID, "false");
                routeContext.render(configuration.getTemplate(), context);
            } else {
                sendContactEmail(fields);
                session.put(Constants.SUCCESS_ID, "true");
                routeContext.render(configuration.getTemplate(), context);
            }

        });
    }

    private void sendContactEmail(List<Field> fields) {
        String name = "No name given.";
        String useremail = "No user email given.";
        String subject = "No subject given.";
        String message = "";

        for (Field field: fields) {
            if (field.getName().equals(Constants.FIELD_NAME)) {
                name = field.getValue();
            } else if (field.getName().equals(Constants.FIELD_USEREMAIL)) {
                useremail = field.getValue();
            } else if (field.getName().equals(Constants.FIELD_SUBJECT)) {
                subject = field.getValue();
            } else {
                if (!message.isEmpty()) {
                    message = message + "\n\n";
                }
                String fieldData = String.format("Field: %s\n%s", field.getName(), field.getValue());
                message = fieldData;
            }
        }

        Email email = EmailBuilder.startingBlank()
                .from(configuration.getFromName(), configuration.getFromAddress())
                .to(configuration.getRecipientName(), configuration.getRecipientAddress())
                .withReplyTo(name, useremail)
                .withSubject(subject)
                .withPlainText(message)
                .buildEmail();

        Mailer mailer = MailerBuilder.buildMailer();
        mailer.sendMail(email);
    }

    private static String decode(String input) {
        try {
            String temporary = URLEncoder.encode(input, "ISO-8859-1");
            return URLDecoder.decode(temporary, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.toString());
        }
        return input;
    }

    public String getMessage(RouteContext routeContext, String key) {
        String lang = routeContext.getParameter("lang").toString();
        if (lang == null) {
            return routeContext.getMessages().get(key, routeContext);
        } else {
            return routeContext.getMessages().get(key, lang);
        }
    }

}
