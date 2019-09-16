package com.wasaymehdi.email;

/**
 * Represents an email being sent to me
 */
final class Email {

    private static final String EMAIL_TO = System.getProperty("com.wasaymehdi.email.to", "wasay40@vt.edu");

    private final String from;
    private final String to;
    private final String subject;
    private final String content;

    private Email(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    static Email forRequest(final EmailRequest emailRequest) {

        return new Email(emailRequest.getFrom(), EMAIL_TO, emailRequest.getSubject(), emailRequest.getContent());

    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
