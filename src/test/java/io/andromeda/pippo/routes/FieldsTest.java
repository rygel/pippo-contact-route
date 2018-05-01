package io.andromeda.pippo.routes;

import io.andromeda.pippo.routes.fields.EmailField;
import io.andromeda.pippo.routes.fields.FixedSubjectField;
import io.andromeda.pippo.routes.fields.FullNameField;
import io.andromeda.pippo.routes.fields.GDPRCheckBox;
import io.andromeda.pippo.routes.fields.MessageField;
import io.andromeda.pippo.routes.fields.PhoneField;
import io.andromeda.pippo.routes.fields.SubjectField;
import org.junit.Test;
import org.mockito.Mockito;
import ro.pippo.core.route.RouteContext;

import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Alexander on 19.03.2017.
 */
public class FieldsTest {

    @Test
    public void testFullNameValue() {
        String expectedValue = "This is a TEST!";
        FullNameField fullNameField = new FullNameField(false);
        fullNameField.setValue(expectedValue);
        String value = fullNameField.getValue();
        assertThat(value, equalTo(expectedValue));
    }

    @Test
    public void testFullNameName() {
        String expectedValue = Constants.FIELD_NAME;
        FullNameField fullNameField = new FullNameField(false);
        String value = fullNameField.getName();
        assertThat(value, equalTo(expectedValue));
    }

    @Test
    public void testFullNameAlert() {
        String expectedValue = "alert_name";
        FullNameField fullNameField = new FullNameField(false);
        String value = fullNameField.getAlert();
        assertThat(value, equalTo(expectedValue));
    }

    @Test
    public void testFullNameDoesValidate() {
        boolean expectedValue = false;
        FullNameField fullNameField = new FullNameField(expectedValue);
        boolean validate = fullNameField.doesValidate();
        assertThat(validate, equalTo(expectedValue));

        expectedValue = true;
        fullNameField = new FullNameField(expectedValue);
        validate = fullNameField.doesValidate();
        assertThat(validate, equalTo(expectedValue));
    }

    @Test
    public void testFullNameValidateEmpty() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_name", Constants.MESSAGE_ID_NAME_EMPTY);
        boolean expectedValue = false;
        FullNameField fullNameField = new FullNameField(expectedValue);

        boolean validate = fullNameField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testFullNameValidateValid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        boolean expectedValue = true;
        FullNameField fullNameField = new FullNameField(expectedValue);
        fullNameField.setValue("Name");

        boolean validate = fullNameField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testEmailValidateEmpty() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_email", Constants.MESSAGE_ID_EMAIL_EMPTY);
        boolean expectedValue = false;
        EmailField emailField = new EmailField(expectedValue);

        boolean validate = emailField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testEmailValidateInvalid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_email", Constants.MESSAGE_ID_EMAIL_INVALID);
        boolean expectedValue = false;
        EmailField emailField = new EmailField(expectedValue);
        emailField.setValue("test");

        boolean validate = emailField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testEmailValidateValid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        boolean expectedValue = true;
        EmailField emailField = new EmailField(expectedValue);
        emailField.setValue("test@test.com");

        boolean validate = emailField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testFixedSubjectValidateEmpty() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_subject", Constants.MESSAGE_ID_SUBJECT_EMPTY);
        boolean expectedValue = false;
        FixedSubjectField fixedSubjectField = new FixedSubjectField("", expectedValue);

        boolean validate = fixedSubjectField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testFixedSubjectValidateValid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        boolean expectedValue = true;
        FixedSubjectField fixedSubjectField = new FixedSubjectField("The subject", expectedValue);

        boolean validate = fixedSubjectField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
        assertThat(fixedSubjectField.getValue(), equalTo("The subject"));
    }

    @Test
    public void testSubjectValidateEmpty() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_subject", Constants.MESSAGE_ID_SUBJECT_EMPTY);
        boolean expectedValue = false;
        SubjectField subjectField = new SubjectField(expectedValue);

        boolean validate = subjectField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testSubjectValidateValid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        boolean expectedValue = true;
        SubjectField subjectField = new SubjectField(expectedValue);
        subjectField.setValue("The subject");

        boolean validate = subjectField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
        assertThat(subjectField.getValue(), equalTo("The subject"));
    }

    @Test
    public void testMessageValidateEmpty() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_message", Constants.MESSAGE_ID_MESSAGE_EMPTY);
        boolean expectedValue = false;
        MessageField messageField = new MessageField(expectedValue);

        boolean validate = messageField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testMessageValidateValid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        boolean expectedValue = true;
        MessageField messageField = new MessageField(expectedValue);
        messageField.setValue("The message");

        boolean validate = messageField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
        assertThat(messageField.getValue(), equalTo("The message"));
    }

    @Test
    public void testPhoneValidateEmpty() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_phone", Constants.MESSAGE_ID_PHONE_EMPTY);
        boolean expectedValue = false;
        PhoneField phoneField = new PhoneField(expectedValue);

        boolean validate = phoneField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testPhoneValidateValid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        boolean expectedValue = true;
        PhoneField phoneField = new PhoneField(expectedValue);
        phoneField.setValue("8128128122");

        boolean validate = phoneField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
        assertThat(phoneField.getValue(), equalTo("8128128122"));
    }

    @Test
    public void testPhoneValidateInvalid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_phone", Constants.MESSAGE_ID_PHONE_INVALID);
        boolean expectedValue = false;
        PhoneField phoneField = new PhoneField(expectedValue);
        phoneField.setValue("test");

        boolean validate = phoneField.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testGDPRValidateEmpty() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        expectedResult.put("alert_gdpr", Constants.MESSAGE_ID_GDPR_FALSE);
        boolean expectedValue = false;
        GDPRCheckBox gdprCheckBox = new GDPRCheckBox(expectedValue);

        boolean validate = gdprCheckBox.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
    }

    @Test
    public void testGDPRValidateValid() {
        Map<String, String> expectedResult = new TreeMap<>();
        Map<String, String> messages = new TreeMap<>();
        boolean expectedValue = true;
        GDPRCheckBox gdprCheckBox = new GDPRCheckBox(expectedValue);
        gdprCheckBox.setValue("true");

        boolean validate = gdprCheckBox.validate(messages);
        assertThat(validate, equalTo(expectedValue));
        assertThat(messages, equalTo(expectedResult));
        assertThat(gdprCheckBox.getValue(), equalTo("true"));
    }

}
