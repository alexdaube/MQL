package com.multitel.mql.mqlservice.domain;

import java.util.UUID;

public class IdGenerator {

    private String defaultID = "";

    public String generate() {
        return UUID.randomUUID().toString();
    }

}
