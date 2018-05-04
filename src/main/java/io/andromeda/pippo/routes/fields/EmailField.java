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

import io.andromeda.pippo.routes.Constants;
import org.hazlewood.connor.bottema.emailaddress.EmailAddressCriteria;
import org.hazlewood.connor.bottema.emailaddress.EmailAddressValidator;

import java.util.Map;

/**
 * A field supporting email addresses.
 * @author Alexander Brandt
 */
public class EmailField extends Field {

    public EmailField(boolean validate) {
        super(Constants.FIELD_USEREMAIL, validate);
    }

    @Override
    public  boolean validate(Map<String, String> messages) {
        if ("".equals(value)) {
            messages.put(alert, Constants.MESSAGE_ID_EMAIL_EMPTY);
            return false;
        }
        if (!EmailAddressValidator.isValid(value, EmailAddressCriteria.RFC_COMPLIANT)) {
            messages.put(alert, Constants.MESSAGE_ID_EMAIL_INVALID);
            return false;
        }
        return true;
    }

}
