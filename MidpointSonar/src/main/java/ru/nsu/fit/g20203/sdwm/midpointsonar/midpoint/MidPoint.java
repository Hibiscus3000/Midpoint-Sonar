package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;


import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;


public class MidPoint {

    private static MidPoint instance;

    private final RequestConfig requestConfig;

    private boolean inited;
    private String uri;
    private String username;
    private String password;

    private MidPoint() {
        requestConfig = RequestConfig.custom().setCircularRedirectsAllowed(true).build();
    }

    public int init(String uri, String username, String password) throws IOException, URISyntaxException, InterruptedException {
        try (CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build()) {
            String userPass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userPass.getBytes()));
            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.AUTHORIZATION, basicAuth);

            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (200 == statusCode) {
                inited = true;
                this.uri = uri;
                this.username = username;
                this.password = password;
            }

            return statusCode;
        }
    }

    public static MidPoint getInstance() {
        if (null == instance) {
            synchronized (MidPoint.class) {
                if (null == instance) {
                    instance = new MidPoint();
                }
            }
        }
        return instance;
    }

    private static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
