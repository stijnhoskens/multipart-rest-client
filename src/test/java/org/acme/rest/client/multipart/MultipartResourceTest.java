package org.acme.rest.client.multipart;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class MultipartResourceTest {

    @ConfigProperty(name = "org.acme.rest.client.multipart.MultipartService/mp-rest/url")
    URI uri;

    @Test
    public void testMultipartDataIsSent() {
        MultipartBody multipartBody = new MultipartBody();
        multipartBody.fileName = "greeting.txt";
        multipartBody.file = new ByteArrayInputStream("HELLO WORLD".getBytes(StandardCharsets.UTF_8));
        Response response = ClientBuilder.newClient().target(uri)
                .request().post(Entity.entity(multipartBody, MediaType.MULTIPART_FORM_DATA_TYPE));
        assertEquals(200, response.getStatus());
    }

}