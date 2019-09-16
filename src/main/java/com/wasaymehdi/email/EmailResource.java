package com.wasaymehdi.email;

import com.wasaymehdi.config.singleton.Singletons;

import javax.mail.MessagingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("email")
public class EmailResource {

    @Context
    private Configuration config;

    @POST
    public Response sendEmail(final EmailRequest emailRequest) {

        final Email email = Email.forRequest(emailRequest);

        try {

            final EmailClient emailClient = Singletons.get(config, EmailClient.class);

            emailClient.send(email);

            return Response.ok().build();

        } catch (MessagingException e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }

    }

}
