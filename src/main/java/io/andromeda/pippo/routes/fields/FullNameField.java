/*
 * Copyright (C) 2018 the original author or authors.
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

import io.andromeda.pippo.routes.Constants;

import java.util.Map;

/**
 * A field supporting the full user name.
 * @author Alexander Brandt
 */
public class FullNameField extends Field {

    public FullNameField(boolean validate) {
        super(Constants.FIELD_NAME, validate);
    }

    @Override
    public  boolean validate(Map<String, String> messages) {
        if ("".equals(value)) {
            messages.put(alert, Constants.MESSAGE_ID_NAME_EMPTY);
            return false;
        }
        return true;
    }

}
