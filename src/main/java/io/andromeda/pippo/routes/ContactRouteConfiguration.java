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

import java.io.File;

/**
 * The configuration class for the contact route.
 * @author Alexander Brandt
 */
public class ContactRouteConfiguration {
    private String url;
    private String template;
    private String formTitle;
    private String recipientName;
    private String recipientAddress;
    private String fromName;
    private String fromAddress;
    private File simplejavamailConfiguration;
    private boolean isTest = false;

    /**
     * Create an instance of the configuration class. The name and email entered by the user of the contact form will be set to the reply to address and name.
     * @param url the url on which the contact form shall be available. Normally it is "contact", which will be translated to yourdomain.com/contact.
     * @param template the name of the template which shall be used to render the get and post actions.
     * @param formTitle the title of the form.
     * @param recipientName The name of the recipient. This should be your name. It will be used when creating the email.
     * @param recipientAddress The email address of the recipient. This should be your email address. It will be used when creating the email.
     * @param fromName The name of the sender. This should be the name of your website application. It will be used when creating the email.
     * @param fromAddress The email address of the sender. This should be your website's email address. It will be used when creating the email.
     */
    public ContactRouteConfiguration(String url, String template, String formTitle, String recipientName,
                                     String recipientAddress, String fromName, String fromAddress) {
        this.url = url;
        this.template = template;
        this.formTitle = formTitle;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.fromName = fromName;
        this.fromAddress = fromAddress;
    }

    /********** Getters ***********************************************************************************************/

    public String getUrl() {
        return url;
    }

    public String getTemplate() {
        return template;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public File getSimplejavamailConfiguration() {
        return simplejavamailConfiguration;
    }

    public boolean getIsTest() {
        return isTest;
    }

    /********** Setters ***********************************************************************************************/
    public void setSimplejavamailConfiguration(File simplejavamailConfiguration) {
        this.simplejavamailConfiguration = simplejavamailConfiguration;
    }

    public void setIsTest(boolean isTest) {
        this.isTest = isTest;
    }
}
