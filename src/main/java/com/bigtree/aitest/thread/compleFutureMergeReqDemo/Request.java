package com.bigtree.aitest.thread.compleFutureMergeReqDemo;

import com.bigtree.aitest.domain.ActIdUser;
import lombok.Data;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author admin
 * @ClassName Request
 * @description: TODO
 * @date 2023年10月11日
 * @version: 1.0
 */
@Data
public class Request {

 String requestId;

 Long userId;

 //无超时机制
 CompletableFuture<ActIdUser> completableFuture;

 // 队列，这个有超时机制
 LinkedBlockingQueue<ActIdUser> usersQueue;

}