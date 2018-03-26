Pippo Contact Route
===================
A custom implementation of a [Pippo](pippo.ro) RouteGroup to handle email sending of a contact form. It uses [Simple Java Mail](http://www.simplejavamail.org) internally.

## Rationale
To be able to create websites with Pippo consistently and effectively this route was created.

## Compatibility
As it uses only the concept of `RouteGroup`s in Pippo it is compatible with all versions from version 1.2.0. As it doesn't use any templates and Pippos internationalization system it is compatible with all template engines and multi-language capable.

## Usage

## Example
```
public class PippoApplication extends Application {
   @Override
    protected void onInit() {
        ContactRouteConfiguration contactConfiguration = new ContactRouteConfiguration(
            "/contact", "contact", "Contact", Example Contact Form",
            "info@example.com", "Example Website From Name", "info@example.com");

        Map<String, Object> context = Constants.getDefaultContext();
        addRouteGroup(new ContactRoute(contactConfiguration, context));

        Map<String, Object> defaultContext = Constants.getDefaultContext();
        String currentPath = System.getProperty("user.dir");
        Configuration rootConfiguration  = new Configuration("root", "/",
                Paths.get(currentPath + "/data/fragments/root"), "", "static_page");
        rootConfiguration.setProtocol(DEFAULT_PROTOCOL);
        rootConfiguration.setDomain(Constants.APPLICATION_DOMAIN_NAME);
        rootConfiguration.setRegisterOverviewRoute(false);

        Fragments rootFragments = new Fragments(this,  defaultContext, rootConfiguration);
    }

}
```



```
public ContactRouteConfiguration(String url, String template, String formTitle, String recipientName,
                                     String recipientAddress, String fromName, String fromAddress) {

ContactRouteConfiguration contactConfiguration = new ContactRouteConfiguration("/contact", "contact",
                "Contact", "Example Contact Form",
                "info@example.com", "Example Name",
                "info@example.com");
        contactConfiguration.setHasSubject(true);
```


## Error handling
To provide good error handling Pippo Contact Route uses the [internationalization](http://www.pippo.ro/doc/internationalization.html) mechanism in Pippo. Below you find the names of the properties and their defaults.

| MESSAGE ID                               | Default Text                                                                                        |
|:-----------------------------------------|:----------------------------------------------------------------------------------------------------|
| contact_route.error.sending              | Error during email sending! Please try again later.                                                 |
| contact_route.error.no_name              | No name given. Please enter your name.                                                              |
| contact_route.error.no_from_address      | No email address given. Please provide your email address so we can answer you.                     |
| contact_route.error.invalid_from_address | Invalid email address given. Please correct your email address so we can answer you.                |
| contact_route.error.no_subject           | No subject given! Please tell us what topic your message is about.                                  |
| contact_route.error.no_telephone         | No telephone number given! Please share your telephone number with us so we can call you back.      |
| contact_route.error.invalid_telephone    | Invalid telephone number given! Please share your telephone number with us so we can call you back. |
| contact_route.error.no_message           | No message given. Please enter your message.                                                        |

```
contact_route.error.sending              = Error during email sending! Please try again later.
contact_route.error.no_name              = No name given. Please enter your name.
contact_route.error.no_from_address      = No email address given. Please provide your email address so we can answer you.
contact_route.error.invalid_from_address = Invalid email address given. Please correct your email address so we can answer you.
contact_route.error.no_subject           = No subject given! Please tell us what topic your message is about.
contact_route.error.no_telephone         = No telephone number given! Please share your telephone number with us so we can call you back.
contact_route.error.invalid_telephone    = Invalid telephone number given! Please share your telephone number with us so we can call you back.
contact_route.error.no_message           = No message given. Please enter your message.
```

## Testing