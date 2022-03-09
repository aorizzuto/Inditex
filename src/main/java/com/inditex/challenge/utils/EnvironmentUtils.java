package com.inditex.challenge.utils;

import org.springframework.core.env.Environment;

import java.util.Arrays;

public class EnvironmentUtils {

    private final Environment env;

    public EnvironmentUtils(Environment env){
        this.env = env;
    }

    public Boolean isLocal() {
        return Arrays.asList(env.getActiveProfiles()).contains("local");
    }

    public Boolean isDev() {
        return Arrays.asList(env.getActiveProfiles()).contains("dev");
    }

    public Boolean isQa() {
        return Arrays.asList(env.getActiveProfiles()).contains("qa");
    }

    public Boolean isProd() {
        return Arrays.asList(env.getActiveProfiles()).contains("prod");
    }

    InditexEnvironment getCurrentEnvironment() {
        if (isLocal()) return InditexEnvironment.LOCAL;
        if (isDev()) return InditexEnvironment.DEV;
        if (isQa()) return InditexEnvironment.QA;
        if (isProd()) return InditexEnvironment.PROD;
        return InditexEnvironment.LOCAL;
    }

    enum InditexEnvironment {
        LOCAL, DEV, QA, PROD
    }
}

