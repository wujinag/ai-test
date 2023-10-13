package com.bigtree.aitest.controller;

import com.bigtree.aitest.domain.ActIdUser;
import com.bigtree.aitest.service.QueryGrantTypeService;
import com.bigtree.aitest.thread.compleFutureMergeReqDemo.Request;
import com.bigtree.aitest.thread.compleFutureMergeReqDemo.UserService;
import com.bigtree.aitest.thread.compleFutureMergeReqDemo.UserWrapBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

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
    @Autowired
    private UserWrapBatchService userBatchService;
    @Autowired
    private UserService userService;

    @PostMapping("/grantType")
    public String test(String resourceName) {
        return queryGrantTypeService.getResult(resourceName);
    }

    /***
     * 请求合并
     * */
    @RequestMapping("/merge")
    public Callable<ActIdUser> merge(Long userId) {
        return () -> userBatchService.queyUserById(userId);
    }

    /**
     *  单独查询
     * @param userId
     *  @author admin
     *  2023/10/10 17:32
     * @return com.bigtree.aitest.domain.ActIdUser
     **/
    @RequestMapping("/mergeSingle")
    public ActIdUser query(Long userId) {
        List<Request> userReqs = new ArrayList<>();
        Request request = new Request();
        request.setRequestId(UUID.randomUUID().toString().replace("-", ""));
        request.setUserId(userId);
        userReqs.add(request);
        return userService.queryUserByIdBatch(userReqs).get(request.getRequestId());
    }

}