package com.bigtree.aitest.thread.compleFutureMergeReqDemo;

import com.bigtree.aitest.domain.ActIdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

/**
 * 包装成批量执行的地方
 *
 * @author admin
 * @ClassName UserWrapBatchService
 * @description: 原理是将用户的请求进行缓存起来，缓存的请求数量达到指定数量或达到定时线程池执行时，
 * 将已有多个单请求处理合并为多处理，调用批量接口进行操作
 * @date 2023年10月09日
 * @version: 1.0
 */
@Service
public class UserWrapBatchService {

    @Autowired
    private UserService userService;

    public static int MAX_TASK_NUM = 100;

    /*
     LinkedBlockingQueue是一个阻塞的队列,内部采用链表的结果,通过两个ReenTrantLock来保证线程安全
     LinkedBlockingQueue与ArrayBlockingQueue的区别
     ArrayBlockingQueue默认指定了长度,而LinkedBlockingQueue的默认长度是Integer.MAX_VALUE,也就是无界队列,在移除的速度小于添加的速度时，容易造成OOM。
     ArrayBlockingQueue的存储容器是数组,而LinkedBlockingQueue是存储容器是链表
     两者的实现队列添加或移除的锁不一样，ArrayBlockingQueue实现的队列中的锁是没有分离的，即添加操作和移除操作采用的同一个ReenterLock锁，
     而LinkedBlockingQueue实现的队列中的锁是分离的，其添加采用的是putLock，移除采用的则是takeLock，这样能大大提高队列的吞吐量，
     也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。
      */
    private final Queue<Request> queue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            //System.out.println("====>执行定时任务，批量整合前端请求<==========");
            int size = queue.size();
            if (size == 0) {
                return;
            }
            //将队列的请求消费到一个集合保存
            List<Request> list = new ArrayList<>();
            System.out.println("合并了[" + size + "]个请求");
            for (int i = 0; i < size; i++) {
                if (i < MAX_TASK_NUM) {
                    list.add(queue.poll());
                }
            }
            //合并请求
            List<Request> usersRqs = new ArrayList<>();
            list.forEach(e -> {
                usersRqs.add(e);
            });
            Map<String, ActIdUser> response = userService.queryUserByIdBatch(usersRqs);

            usersRqs.forEach(e -> {
                ActIdUser user = response.get(e.getRequestId());
                e.usersQueue.offer(user);
            });

        }, 100, 10, TimeUnit.MILLISECONDS);

    }

    /**
     * demo 查询详情
     *
     * @param userId
     * @return com.bigtree.aitest.domain.ActIdUser
     * @author admin
     * 2023/10/10 14:05
     **/
    public ActIdUser queyUserById(Long userId) {

        Request request = new Request();
        request.requestId = UUID.randomUUID().toString().replace("-", "");
        request.userId = userId;
//      CompletableFuture<ActIdUser> future = new CompletableFuture<>();
//      request.completableFuture = future;
        LinkedBlockingQueue<ActIdUser> usersQueue = new LinkedBlockingQueue<>();
        request.usersQueue = usersQueue;
        //将对象传入队列
        queue.offer(request);
        try {
            return usersQueue.poll(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("e = " + e);
        }
        return null;
    }


}