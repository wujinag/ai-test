package com.bigtree.aitest.controller;

import com.bigtree.aitest.service.QueryGrantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @ClassName TestController
 * @description: TODO
 * @date 2023年08月30日
 * @version: 1.0
 */
@RestController
public class TestController {
    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @PostMapping("/grantType")
    public String test(String resourceName) {
        return queryGrantTypeService.getResult(resourceName);
    }

}