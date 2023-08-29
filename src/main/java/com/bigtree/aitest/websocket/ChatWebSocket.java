package com.bigtree.aitest.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author admin
 * @ClassName ChatWebSocket
 * @description: websocket实现简单的聊天服务
 * @date 2023年08月14日
 * @version: 1.0
 */
@Slf4j
@Component
@ServerEndpoint(value = "/ws/{username}",configurator = MySpringConfigurator.class)
public class ChatWebSocket {

    /**
     * 连接成功
     *
     * @param username
     * @param session
     * @return void
     * @author admin
     * 2023/8/14 11:30
     **/
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
            WebSocketUtils.online_user_sessions.put(username, session);
            WebSocketUtils.sendMsgAll(username,username+"===>已经登录!!!!");
            //WebSocketUtils.sendMsg(session, "");
            System.out.println("客户端【" + username + "】,连接成功!!");
    }

    @OnMessage
    public void onMsg(@PathParam("username") String username, String msg) {
            System.out.println("服务器收到【" + username + "】,信息:" + msg);
            WebSocketUtils.sendMsgAll(username,"【" + username + "】:" + msg);
    }

    @OnClose
    public void OnClose(@PathParam("username") String username, Session session) {
            WebSocketUtils.online_user_sessions.remove(username);
            WebSocketUtils.sendMsgAll(username,"【" + username + "】退出登录！！！");
            System.out.println("客户端【" + username + "】,断开连接!!"+session.getId());
    }


    @OnError
    public void OnError(Session session, Throwable throwable) throws IOException {
        System.out.printf("发生了错误===>"+throwable);
        session.close();
    }

}