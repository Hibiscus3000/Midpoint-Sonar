package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.HttpHeaders;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.exception.MidPointNotAuthenticatedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;


public class MidPoint {

    private static MidPoint instance;

    private final RequestConfig requestConfig;

    private boolean authenticated;
    private String uri = "http://midpoint:8080/midpoint/ws/rest/tasks/";
    //private String uri = "http://localhost:8080/midpoint/ws/rest/tasks/";
    private final static String uriEnd = "tasks";
    private String username;
    private String password;

    private final static int sleepInterval = 500;

    private final static String containerFilePath = "/opt/midpoint/var/";
    private final static String volumeFilePath = "/app/midpoint/";

    private MidPoint() {
        requestConfig = RequestConfig.custom().setCircularRedirectsAllowed(true).build();
    }

    public void setURI(String uri) {
        this.uri = uri;
    }

    public int attemptAuth(String username, String password) throws IOException {
        try (CloseableHttpClient client = buildClient()) {
            this.username = username;
            this.password = password;

            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(getUserPass().getBytes()));
            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.AUTHORIZATION, basicAuth);

            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (200 == statusCode) {
                authenticated = true;
            }

            return statusCode;
        }
    }

    private CloseableHttpClient buildClient() {
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
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

    public void runTask(String oid) throws IOException, MidPointNotAuthenticatedException {
        if (!authenticated) {
            throw new MidPointNotAuthenticatedException();
        }
        String command = String.format("curl --user %s:%s -X POST %s%s/run",
                username, password, uri, oid);
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.start();
    }

    public CompletableFuture<String> getTaskRunResult(String oid, String uriEnd) {
        if (null == oid) {
            return CompletableFuture.completedFuture(null);
        }
        final CloseableHttpClient client = buildClient();
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(getUserPass().getBytes()));
        HttpGet request = new HttpGet(uri.replaceAll(MidPoint.uriEnd, uriEnd) + oid);
        request.setHeader(HttpHeaders.AUTHORIZATION, basicAuth);
        return CompletableFuture.supplyAsync(() -> {
            do {
                try {
                    final HttpResponse response = client.execute(request);
                    if (2 == response.getStatusLine().getStatusCode() / 100) {
                        HttpEntity entity = response.getEntity();
                        InputStream inputStream = entity.getContent();
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
                            final StringBuilder stringBuilder = new StringBuilder();
                            String inputLine;
                            while ((inputLine = in.readLine()) != null)
                                stringBuilder.append(inputLine);
                            client.close();
                            return new String(stringBuilder);
                        }
                    }
                    Thread.sleep(sleepInterval);
                } catch (Exception e) {
                    try {
                        client.close();
                    } catch (IOException ex) {
                    }
                    return null;
                }
            } while (true);
        });
    }

    public String getFilePath(String filePath) {
        return filePath.replaceAll(containerFilePath, volumeFilePath);
    }

    private String getUserPass() {
        return username + ":" + password;
    }
}
