package com.bigtree.aitest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author admin
 * @ClassName UserServiceInvocationHandler
 * @description: TODO
 * @date 2023年10月08日
 * @version: 1.0
 */
public class UserServiceInvocationHandler implements InvocationHandler {

    private Object target;

    public UserServiceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" beforer call,targetProxy==>"+ proxy.getClass() + "method==>" + method.getName());
        Object result = method.invoke(target, args);
        System.out.println(" after call method : " + method.getName());
        return result;
    }
}