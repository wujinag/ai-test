package com.bigtree.aitest.proxy;

import java.lang.reflect.Proxy;

/**
 * @author admin
 * @ClassName testProxy
 * @description: 在代码中，我们通过调用Proxy类的静态方法newProxyInstance()来创建代理对象。
 * 该方法需要三个参数：被代理类的类加载器、被代理类的接口列表以及一个InvocationHandler对象。
 * 当代理对象调用被代理类的方法时，实际上是调用了InvocationHandler对象的invoke()方法。
 * 在invoke()方法中，我们可以先执行一些额外的逻辑，然后再调用被代理类的方法。
 * 最后，我们将代理对象的getUsername()方法返回的结果打印出来
 * @date 2023年10月08日
 * @version: 1.0
 */
public class TestProxy {
    public static void main(String[] args) {
        UserProxyService userService = new UserProxyServiceImpl();
        UserProxyService proxyInstance = (UserProxyService) Proxy.newProxyInstance(
                UserProxyService.class.getClassLoader(), new Class[]{UserProxyService.class},
                                new UserServiceInvocationHandler(userService));
        String userName = proxyInstance.getUserName();
        System.out.printf("====>"+userName);
    }
}