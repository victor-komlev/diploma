package com.mera.cs.eventprocessing.client.websocket.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class MyOwnObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public MyOwnObjectMapper() {
        registerModule(new SimpleModule());
    }
}
