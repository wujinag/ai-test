package com.bigtree.aitest.thread.compleFutureMergeReqDemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigtree.aitest.domain.ActIdUser;
import com.bigtree.aitest.mapper.ActIdUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author admin
 * @ClassName UserServiceImpl
 * @description: TODO
 * @date 2023年10月10日
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ActIdUserMapper mapper;

    @Override
    public Map<String, ActIdUser> queryUserByIdBatch(List<Request> userReqs) {
       List<Long> userIds = userReqs.stream().map(Request::getUserId).collect(Collectors.toList());
        QueryWrapper<ActIdUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("ID_",userIds);
        List<ActIdUser> actIdUsers = mapper.selectList(queryWrapper);
        Map<String, List<ActIdUser>> usersGroup = actIdUsers.stream().collect(Collectors.groupingBy(ActIdUser::getId_));
        Map<String, ActIdUser> result = new HashMap<>();
        userReqs.forEach(val->{
            List<ActIdUser> actIdUsers1 = usersGroup.get(val.getUserId());
            if (actIdUsers1.isEmpty()) {
                result.put(val.getRequestId(),new ActIdUser());
            }else {
                result.put(val.requestId,actIdUsers1.get(0));
            }
        });
        return result;
    }
}