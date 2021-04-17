package org.acme.rest.client.multipart;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class MultipartResourceTest {

    @ConfigProperty(name = "org.acme.rest.client.multipart.MultipartService/mp-rest/url")
    URI uri;

    @Test
    public void testMultipartDataIsSent() {
        MultipartBody multipartBody = new MultipartBody();
        multipartBody.fileName = "greeting.txt";
        multipartBody.file = new ByteArrayInputStream("HELLO WORLD".getBytes(StandardCharsets.UTF_8));
        MultipartService multipartService =
                RestClientBuilder.newBuilder().baseUri(uri)
                        .build(MultipartService.class);
        assertNotNull(multipartService.sendMultipartData(multipartBody));
    }

}