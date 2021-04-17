package org.acme.rest.client.multipart.server;

import org.acme.rest.client.multipart.MultipartBody;
import org.acme.rest.client.multipart.MultipartService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/echo")
public class EchoService implements MultipartService {

    @Override
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String sendMultipartData(@MultipartForm MultipartBody data) {
        return String.format("Successfully uploaded %s", data.fileName);
    }
}