package cn.wolfcode;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/{token}")
@Component
public class WSServer {
    private Session session;
    public static ConcurrentHashMap<String,Session> clients = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session, @PathParam( "token") String token){
        System.out.println("浏览器和服务器建立连接");
        //建立和浏览器会话的映射关系
        clients.put(token,session);
    }
    @OnMessage
    public void onMessage(@PathParam( "token") String token, String msg){
        System.out.println("收到的客户端标识为：" + token + "，发送的消息为" + msg);
    }
    @OnClose
    public void onClose(@PathParam( "token") String token){
        System.out.println("浏览器和服务器断开连接");
        clients.remove(token);
    }
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }
}
