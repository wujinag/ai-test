package com.bigtree.aitest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author admin
 * @ClassName QueryGrantTypeService
 * @description: TODO
 * @date 2023年08月30日
 * @version: 1.0
 */
@Service
public class QueryGrantTypeService {

    @Autowired
    private GrantTypeSerive grantTypeSerive;

    private Map<String, Function<String,String>>  grantTypeMap = new HashMap<>();

    @PostConstruct
    public void dispatcherInit(){
        grantTypeMap.put("红包",resourceId-> grantTypeSerive.redPaper(resourceId));
        grantTypeMap.put("购物券",resourceId-> grantTypeSerive.shopping(resourceId));
        grantTypeMap.put("qq会员",resourceId-> grantTypeSerive.QQVip(resourceId));
    }

    public String  getResult( String resourceType){
        Function<String, String> result = grantTypeMap.get(resourceType);
        if (result != null) {
            return result.apply(resourceType);
        }
        return "查询不到优惠券的发放方式";
    }


}