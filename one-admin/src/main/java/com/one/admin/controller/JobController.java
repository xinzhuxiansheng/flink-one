package com.one.admin.controller;


import com.one.admin.data.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {

    @GetMapping("/start")
    public Result<List<String>> printMembers() {
//        List<String> members = hazelcastInstance.getCluster().getMembers().stream().map(m->m.getSocketAddress().toString()).collect(Collectors.toList());
        return Result.succeed(Collections.emptyList());

    }
}
