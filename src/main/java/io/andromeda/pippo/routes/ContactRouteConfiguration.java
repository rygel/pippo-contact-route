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
import java.util.Map;

/**
 * Created by Alexander on 20.02.2017.
 */
public class ContactRouteConfiguration {
    private String url;
    private String template;
    private String formTitle;
    private String recipientName;
    private String recipientAddress;
    private String fromName;
    private String fromAddress;
    private boolean hasSubject;
    private String fixedSubject = "Default Subject";
    private boolean hasTelephone;
    private File simplejavamailConfiguration;
    private Map<String, String> additionalFields;

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

    public boolean getHasSubject() {
        return hasSubject;
    }

    public String getFixedSubject() {
        return fixedSubject;
    }

    public boolean getHasTelephone() {
        return hasTelephone;
    }

    public File getSimplejavamailConfiguration() {
        return simplejavamailConfiguration;
    }

    public Map<String, String> getAdditionalFields() {
        return additionalFields;
    }

    /********** Setters ***********************************************************************************************/

    public void setHasSubject(boolean hasSubject) {
        this.hasSubject = hasSubject;
    }

    public void setFixedSubject(String fixedSubject) {
        this.fixedSubject = fixedSubject;
    }

    public void setHasTelephone(boolean hasTelephone) {
        this.hasTelephone = hasTelephone;
    }

    public void setSimplejavamailConfiguration(File simplejavamailConfiguration) {
        this.simplejavamailConfiguration = simplejavamailConfiguration;
    }

    public void setAdditionalFields(Map<String, String> additionalFields) {
        this.additionalFields.putAll(additionalFields);
    }
}
