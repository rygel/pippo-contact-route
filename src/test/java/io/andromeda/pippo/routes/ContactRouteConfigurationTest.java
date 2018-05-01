package io.andromeda.pippo.routes;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Alexander on 19.03.2017.
 */
public class ContactRouteConfigurationTest {

    @Test
    public void testContactConfiguration() {
        String expectedUrl = "";
        String expectedTemplate = "";
        String expectedFormTitle = "";
        String expectedRecipientName = "";
        String expectedRecipientAddress = "";
        String expectedFromName = "";
        String expectedFromAddress = "";
        ContactRouteConfiguration contactRouteConfiguration
                = new ContactRouteConfiguration(expectedUrl, expectedTemplate, expectedFormTitle, expectedRecipientName,
                expectedRecipientAddress, expectedFromName, expectedFromAddress);
        File expectedFile = new File("");
        boolean isTest = true;
        contactRouteConfiguration.setSimplejavamailConfiguration(expectedFile);
        contactRouteConfiguration.setIsTest(isTest);

        assertThat(contactRouteConfiguration.getUrl(), equalTo(expectedUrl));
        assertThat(contactRouteConfiguration.getTemplate(), equalTo(expectedTemplate));
        assertThat(contactRouteConfiguration.getFormTitle(), equalTo(expectedFormTitle));
        assertThat(contactRouteConfiguration.getRecipientName(), equalTo(expectedRecipientName));
        assertThat(contactRouteConfiguration.getRecipientAddress(), equalTo(expectedRecipientAddress));
        assertThat(contactRouteConfiguration.getFromName(), equalTo(expectedFromName));
        assertThat(contactRouteConfiguration.getFromAddress(), equalTo(expectedFromAddress));
        assertThat(contactRouteConfiguration.getSimplejavamailConfiguration(), equalTo(expectedFile));
        assertThat(contactRouteConfiguration.getIsTest(), equalTo(isTest));
    }

}
