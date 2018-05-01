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
package io.andromeda.pippo.routes.fields;

import ro.pippo.core.route.RouteContext;

import java.util.Map;

/**
 * The abstract field which is the basis of the Fields.
 * @author Alexander Brandt
 */
public abstract class Field {
    String name;
    String alert;
    String value = "";
    boolean doesValidate;

    public Field(String name, boolean validate) {
        this.name = name;
        this.alert = "alert_" + name;
        this.doesValidate = validate;
    }

    public String getName() {
        return name;
    }

    public String getAlert() {
        return alert;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean doesValidate() {
        return doesValidate;
    }

    public boolean validate(RouteContext routeContext) {
        return false;
    }

    public boolean validate(Map<String, String> messages) {
        return false;
    }

}
