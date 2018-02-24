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

import org.apache.commons.validator.routines.EmailValidator;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.util.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.route.RouteGroup;

import javax.mail.Message;
import java.util.Map;

/**
 * The route handling the contacts page
 */
public class ContactRoute extends RouteGroup {
    /** The logger instance for this class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactRoute.class);
    private static final String TITLE_ID = "title";
    private static final String SUCCESS_ID = "success";
    private static final String ID_NAME = "name";
    private static final String ID_EMAIL = "email";
    private static final String ID_SUBJECT = "subject";
    private static final String ID_MESSAGE = "message";
    //
    private ContactRouteConfiguration configuration;


    public ContactRoute(ContactRouteConfiguration configuration, Map<String, Object> defaultContext) {
        super(configuration.getUrl());

        /* Override the default Simple Java Mail configuration */
        if (configuration.getSimplejavamailConfiguration() != null) {
            ConfigLoader.loadProperties(configuration.getSimplejavamailConfiguration(), false);
        }

        this.configuration = configuration;

        GET(configuration.getUrl(), routeContext -> {
            routeContext.render(configuration.getTemplate(), defaultContext);
        });

        POST(configuration.getUrl(), routeContext -> {
            Map<String, Object> context = defaultContext;
            context.put(TITLE_ID, configuration.getFormTitle());

            String name = routeContext.getParameter(ID_NAME).toString();
            String useremail = routeContext.getParameter(ID_EMAIL).toString();
            String subject = configuration.getFixedSubject();
            if (configuration.getHasSubject()) {
                subject = routeContext.getParameter(ID_SUBJECT).toString();
            }
            String message = routeContext.getParameter(ID_MESSAGE).toString();

            LOGGER.info(String.format("name: %s, email: %s, subject: %s, message: %s", name, useremail, subject, message));
            context.put(ID_NAME, name);
            context.put(ID_EMAIL, useremail);
            context.put(ID_SUBJECT, subject);
            context.put(ID_MESSAGE, message);

            boolean everythingOk = true;
            everythingOk = checkName(name, context) && everythingOk;
            everythingOk = checkEmail(useremail, context) && everythingOk;
            everythingOk = checkSubject(subject, context) && everythingOk;
            everythingOk = checkMessage(message, context) && everythingOk;

            if (!everythingOk) {
                context.put(SUCCESS_ID, "false");
                routeContext.render(configuration.getTemplate(), context);
            } else {
                sendContactEmail(context);
                context.put(SUCCESS_ID, "true");
                routeContext.render(configuration.getTemplate(), context);
            }
        });
    }

    private void sendContactEmail(Map<String, Object> context) {
        ConfigLoader.loadProperties("conf/simplejavamail.properties", false); // optional default

        Email email = new Email();
        email.addRecipient(configuration.getRecipientName(), configuration.getRecipientAddress(), Message.RecipientType.TO);
        email.setFromAddress(configuration.getFromName(), configuration.getFromAddress());
        email.setReplyToAddress((String) context.get(ID_NAME), (String) context.get(ID_EMAIL));
        email.setSubject((String) context.get(ID_SUBJECT));
        email.setText((String) context.get(ID_MESSAGE));

        new Mailer().sendMail(email);
    }

    private boolean checkName(final String name, Map<String, Object> context) {
        if (name.isEmpty()) {
            context.put("alert_name", "Please enter your name!");
            return false;
        }
        return true;
    }

    private boolean checkEmail(final String email, Map<String, Object> context) {
        if (email.isEmpty()) {
            context.put("alert_email", "Please enter your email address!");
            return false;
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            context.put("alert_email", "Email is not valid!");
            return false;
        }

        return true;
    }

    private boolean checkSubject(final String subject, Map<String, Object> context) {
        if (subject.isEmpty()) {
            context.put("alert_subject", "Please enter a subject!");
            return false;
        }
        return true;
    }

    private boolean checkMessage(final String message, Map<String, Object> context) {
        if (message.isEmpty()) {
            context.put("alert_message", "Please enter your message!");
            return false;
        }
        return true;
    }

    private String createMessageText(Map<String, Object> context) {
        String result = "";
        Map<String, String> additionalFields = configuration.getAdditionalFields();
        if (additionalFields != null) {
            for (Map.Entry<String, String> entry : additionalFields.entrySet()) {
                if (context.containsKey(entry.getKey())) {
                    result += String.format("%s: %s%n", entry.getValue(),context.get(entry.getKey()));
                }
            }
        }
        result += (String) context.get(ID_MESSAGE);

        return result;
    }

}
