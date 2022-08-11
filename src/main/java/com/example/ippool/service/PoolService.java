package com.example.ippool.service;

import java.io.IOException;

public interface PoolService {
    Object getIp();

    Object putIp() throws IOException;
}
