package com.bigtree.aitest.service;

import org.springframework.stereotype.Service;

/**
 * @author admin
 * @ClassName GrantTypeSerive
 * @description: TODO
 * @date 2023年08月30日
 * @version: 1.0
 */
@Service
public class GrantTypeSerive {

    public String redPaper(String resourceId) {
        return "每周五八点发放红包";
    }

    public String shopping(String resourceId) {
        return "每周三发放购物券";
    }

    public String QQVip(String resourceId) {
        return "每周一九点秒杀";
    }
}