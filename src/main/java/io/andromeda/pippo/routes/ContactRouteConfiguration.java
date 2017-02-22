package io.andromeda.pippo.routes;

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
}
