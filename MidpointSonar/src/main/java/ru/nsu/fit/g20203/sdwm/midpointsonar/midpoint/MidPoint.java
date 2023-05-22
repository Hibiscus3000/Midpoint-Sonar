package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import com.evolveum.midpoint.client.api.exception.SchemaException;
import com.evolveum.midpoint.client.impl.prism.RestPrismService;
import com.evolveum.midpoint.client.impl.prism.RestPrismServiceBuilder;

import java.io.FileNotFoundException;

public class MidPoint {

    private static String url;
    private static String username;
    private static String password;

    public static void init(String url, String login, String password) throws SchemaException, FileNotFoundException {
        MidPoint.url = url;
        username = login;
        MidPoint.password = password;
        getService();
    }

    public static RestPrismService getService() throws SchemaException, FileNotFoundException {
        return RestPrismServiceBuilder.create()
                .baseUrl(MidPoint.url)
                .username(username)
                .password(MidPoint.password)
                .build();
    }
}
