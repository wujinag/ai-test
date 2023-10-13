package com.bigtree.aitest.proxy;

import org.springframework.stereotype.Service;

/**
 * @author admin
 * @ClassName UserServiceImpl
 * @description: TODO
 * @date 2023年10月08日
 * @version: 1.0
 */
public class UserProxyServiceImpl implements UserProxyService {
  @Override
  public String getUserName() {
   return "bigTree";
  }
}