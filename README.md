Pippo Contact Route
===================
[![Build Status](https://travis-ci.org/rygel/pippo-contact-route.svg?branch=master)](https://travis-ci.org/rygel/pippo-contact-route)
[![Coverage Status](https://coveralls.io/repos/github/rygel/pippo-contact-route/badge.svg?branch=master)](https://coveralls.io/github/rygel/pippo-contact-route?branch=master)
[![Maven Central](http://img.shields.io/maven-central/v/io.andromeda.pippo/pippo-contact-route.svg)](http://search.maven.org/#search|ga|1|io.andromeda.pippo)
[![Javadocs](http://www.javadoc.io/badge/io.andromeda.pippo/pippo-contact-route.svg)](http://www.javadoc.io/doc/io.andromeda/pippo-contact-route)


A custom implementation of a [Pippo](pippo.ro) RouteGroup to handle email sending of a contact form. It uses [Simple Java Mail](http://www.simplejavamail.org) internally.

## Rationale
To be able to create websites with Pippo consistently and effectively this route was created.

## Compatibility
As it uses only the concept of `RouteGroup`s in Pippo it is compatible with all versions from version 1.2.0. As it doesn't use any templates and Pippos internationalization system it is compatible with all template engines and multi-language capable.

## Usage
1) Add the dependency to your pom.xml.
   ```xml
        <dependency>
            <groupId>io.andromeda.pippo</groupId>
            <artifactId>pippo-contact-route</artifactId>
            <version>2.0.1</version>
        </dependency>
   ```

2) Configure the route and add it to the application
```java
public class PippoApplication extends Application {
   @Override
    protected void onInit() {
        /* Here the contact route is configured. */
        ContactRouteConfiguration contactConfiguration = new ContactRouteConfiguration("/contact", "contact",
                "Contact", "Example Contact Form",
                "info@example.com", "Example Name",
                "info@example.com");
        /* Get the contect for this route. Use an empty map if there is no context. */
        Map<String, Object> context = getDefaultContext();

        /* Create a list of fields you want to have on the contact form. */
        List<Field> fields = new ArrayList<>();
        fields.add(new FullNameField(false)); // Full name of the user
        fields.add(new EmailField(true));     // Email of the user, will be validated
        fields.add(new SubjectField(false));  // The subject as entered
        fields.add(new MessageField(false));  // The message content
        fields.add(new GDPRCheckBox(true));   // The GDPR checkbox, will be validated
        /* Here the route is added to the Pippo application. */
        addRouteGroup(new ContactRoute(contactConfiguration, fields, context));
    }
}
```

3) Add a ```simplejavamail.properties``` file under ```src/main/resources``` with the configuration of your email server.
Please refer to the [Simple Java Mail Website](http://www.simplejavamail.org/#/configuration) for details.

This is an example configuration file. Please be careful and don't check this file into a public git reporitory.
```
simplejavamail.transportstrategy=SMTP_TLS
simplejavamail.smtp.host=<host_url>
simplejavamail.smtp.port=<smtp_port>
simplejavamail.smtp.username=<username>
simplejavamail.smtp.password=<password>
```

4) Add the internationalized error messages to your project. You can find them in ```src/test/resources/conf```.
 They need to add them to the corresponding ```src/main/resources/conf/messages.properties``` files.
 Currently there are error messages in English and German.

English
```
andromeda.pippo.contactroute.email.empty = Please enter your email address!
andromeda.pippo.contactroute.email.invalid = Please enter a valid email address!
andromeda.pippo.contactroute.name.empty = Please enter your name!
andromeda.pippo.contactroute.subject.empty = Please enter your subject!
andromeda.pippo.contactroute.message.empty = Please enter your message!
andromeda.pippo.contactroute.phone.empty = Please enter your phone number!
andromeda.pippo.contactroute.phone.invalid = Please enter a valid phone number!
andromeda.pippo.contactroute.gdpr.false = Please allow us to use your data internally!
```

German
```
andromeda.pippo.contactroute.email.empty = Bitte geben Sie Ihre Emailadresse ein!
andromeda.pippo.contactroute.email.invalid = Bitte geben Sie eine gültige Emailadresse ein!
andromeda.pippo.contactroute.name.empty = Bitte geben Sie Ihren Namen ein!
andromeda.pippo.contactroute.subject.empty = Bitte geben Sie Ihren Betreff ein!
andromeda.pippo.contactroute.message.empty = Bitte geben Sie Ihre Nachricht ein!
andromeda.pippo.contactroute.phone.empty = Bitte geben Sie Ihre Telefonnummer ein!
andromeda.pippo.contactroute.phone.invalid = Bitte geben Sie eine gültige Telefonnummer ein!
andromeda.pippo.contactroute.gdpr.false = Bitte geben Sie uns Ihre Erlaubnis Ihre Daten zu verarbeiten
```


## Example
```java
public ContactRouteConfiguration(String url, String template, String formTitle, String recipientName,
                                     String recipientAddress, String fromName, String fromAddress) {

        ContactRouteConfiguration contactConfiguration = new ContactRouteConfiguration("/contact", "contact",
                "Contact", "Example Contact Form",
                "info@example.com", "Example Name",
                "info@example.com");
```


## Error handling
To provide good error handling Pippo Contact Route uses the [internationalization](http://www.pippo.ro/doc/internationalization.html) mechanism in Pippo. Below you find the names of the properties and their defaults.

```
andromeda.pippo.contactroute.email.empty = Please enter your email address!
andromeda.pippo.contactroute.email.invalid = Please enter a valid email address!
andromeda.pippo.contactroute.name.empty = Please enter your name!
andromeda.pippo.contactroute.subject.empty = Please enter your subject!
andromeda.pippo.contactroute.message.empty = Please enter your message!
andromeda.pippo.contactroute.phone.empty = Please enter your phone number!
andromeda.pippo.contactroute.phone.invalid = Please enter a valid phone number!
andromeda.pippo.contactroute.gdpr.false = Please allow us to use your data internally!
```
