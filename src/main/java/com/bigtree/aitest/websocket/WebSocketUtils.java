package com.bigtree.aitest.websocket;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author admin
 * @ClassName WebSocketUtils
 * @description: TODO
 * @date 2023年08月14日
 * @version: 1.0
 */
public class WebSocketUtils {

    public static final Map<String, Session> online_user_sessions = new ConcurrentHashMap<>();


    public static void sendMsg(Session session, String msg) {
        if (session == null) {
            return;
        }
        if (!session.isOpen()) {
            System.out.println("消息推送失败，session 处于关闭状态:" + session.getId());
            return;

        }
        synchronized (session) {

            try {

                Thread.sleep(1000 * 1);

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            if (session.isOpen()) {

                try {

                    session.getBasicRemote().sendText(msg);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {

                System.out.printf("消息推送失败，session 处于关闭状态:" + session.getId());

            }

        }


    }

    public static void sendMsgAll(String username,String msg) {
        online_user_sessions.forEach(((s, session) -> {
            if(!WebSocketUtils.online_user_sessions.containsKey(username)) {
                sendMsg(session, msg);
            }
        }));
    }
}