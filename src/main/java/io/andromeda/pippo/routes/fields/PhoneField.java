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

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A field supporting a phone number.
 * @author Alexander Brandt
 */
public class PhoneField extends Field {

    public PhoneField(boolean validate) {
        super(Constants.FIELD_PHONE, validate);
    }

    @Override
    public  boolean validate(Map<String, String> messages) {
        if (value.equals("")) {
            messages.put(alert, Constants.MESSAGE_ID_PHONE_EMPTY);
            return false;
        } else if (!validatePhoneNumber(value)) {
            messages.put(alert, Constants.MESSAGE_ID_PHONE_INVALID);
            return false;
        }
        return true;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {

        /*
        8128128122
        812 787 7898
        812 897-8978
         */
        //String phoneNumber = "1-(80..2)-321-0361";
        //System.out.println(phoneNumber.length());
        String regex = "^\\+?[0-9. ()-]{10,25}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

}
