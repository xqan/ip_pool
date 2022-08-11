package com.example.ippool.controller;

import com.example.ippool.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pool")
public class PoolController {

    final PoolService poolService;

    public PoolController(PoolService poolService) {
        this.poolService = poolService;
    }

    @PutMapping("/ip")
    public Object putIp() throws IOException {
        return poolService.putIp();
    }

    @GetMapping("/ip")
    public Object getIp() {
        return poolService.getIp();
    }
}
