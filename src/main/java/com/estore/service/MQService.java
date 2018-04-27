package com.estore.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface MQService {
    void sendMessage(Object message) throws IOException, TimeoutException;
}
