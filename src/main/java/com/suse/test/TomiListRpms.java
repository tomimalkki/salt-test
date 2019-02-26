package com.suse.test;

import com.google.gson.reflect.TypeToken;
import com.suse.salt.netapi.AuthModule;
import com.suse.salt.netapi.calls.LocalCall;
import com.suse.salt.netapi.client.SaltClient;
import com.suse.salt.netapi.client.impl.HttpAsyncClientImpl;
import com.suse.salt.netapi.datatypes.AuthMethod;
import com.suse.salt.netapi.datatypes.PasswordAuth;
import com.suse.salt.netapi.datatypes.target.Glob;
import com.suse.salt.netapi.results.Result;
import com.suse.salt.netapi.utils.HttpClientUtils;

import java.net.URI;
import java.util.*;

public class TomiListRpms {

    private static final String SALT_API_URL = "http://10.10.121.203:8000";
    private static final String USER = "saltdev";
    private static final String PASSWORD = "foo2";
    static final AuthMethod AUTH = new AuthMethod(new PasswordAuth(USER, PASSWORD, AuthModule.PAM));


    public static void main(String[] args) {
        SaltClient client = new SaltClient(URI.create(SALT_API_URL),
                new HttpAsyncClientImpl(HttpClientUtils.defaultClient()));

        Map<String, Result<List<String>>> minionsRpms = listRpms().callSync(client, new Glob("*"), AUTH).toCompletableFuture().join();
        for (String minion : minionsRpms.keySet()) {
            System.out.println(minion+" installed RPM packages:");
            List<String> rpmsList = minionsRpms.get(minion).result().get();
            rpmsList.sort(String::compareToIgnoreCase);
            for (String rpm : rpmsList) {
                System.out.println("  "+rpm);
            }

        }
        System.exit(1);
    }

    public static LocalCall<List<String>> listRpms() {
        return new LocalCall("tomi.list_rpms", Optional.empty(), Optional.empty(), new TypeToken<List<String>>() {
        });
    }


}
