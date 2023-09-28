package com.bigtree.aitest.thread;

import org.redisson.client.handler.ConnectionWatchdog;

import java.util.concurrent.*;

/**
 * @author admin
 * @ClassName CompletableFutureService
 * @description: TODO
 * @date 2023年09月25日
 * @version: 1.0
 */
public class CompletableFutureService {

 public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
  ExecutorService executorService = Executors.newFixedThreadPool(10);

//  CountDownLatch latch = new CountDownLatch(2);
//  Future<String> say = executorService.submit(()->{
//   System.out.println("说话 ...");
//   latch.countDown();
//   return "说话";
//  });
//
//  Future<String> drink = executorService.submit(()->{
//   System.out.println("喝水 ...");
//   latch.countDown();
//   return "喝水";
//  });
//  latch.await();
//  Future<String> go = executorService.submit(()->{
//   System.out.println("行动 ...");
//   return "go last";
//  });
//  System.out.printf(go.get());

  testCompleFutreTask(executorService);
 }

 public static void testCompleFutreTask(ExecutorService executorService) throws ExecutionException, InterruptedException, TimeoutException {
   CompletableFuture<String> say = CompletableFuture.supplyAsync(()->{
    return "say";
   },executorService);
//  say.get();
  CompletableFuture<String> drink = CompletableFuture.supplyAsync(()->{
   return "drink";
  },executorService);
//drink.get();
  CompletableFuture<String> stringCompletableFuture = drink.thenCombine(drink, (sayAction, drinkAction) ->{
   try {
    return say.get(1, TimeUnit.MINUTES)+drink.get(1,TimeUnit.MINUTES)+"end";
   } catch (InterruptedException e) {
    throw new RuntimeException(e);
   } catch (ExecutionException e) {
    throw new RuntimeException(e);
   } catch (TimeoutException e) {
    throw new RuntimeException(e);
   }
  });
  stringCompletableFuture.join();
  System.out.println("===>"+stringCompletableFuture.get(1,TimeUnit.MINUTES));
 }


}