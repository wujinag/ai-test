package com.bigtree.aitest.thread.compleFutureMergeReqDemo;

import com.bigtree.aitest.domain.ActIdUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @ClassName UserSerive
 * @description: TODO
 * @date 2023年10月08日
 * @version: 1.0
 */

public interface UserService {

   Map<String, ActIdUser> queryUserByIdBatch(List<Request> userReqs);

}