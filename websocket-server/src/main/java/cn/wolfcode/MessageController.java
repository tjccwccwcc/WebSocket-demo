package cn.wolfcode;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.io.IOException;

@RestController
public class MessageController {
    @RequestMapping("/sendMsg")
    public String sendMsg(String token, String msg) throws IOException {
        Session session = WSServer.clients.get(token);
        session.getBasicRemote().sendText(msg);
        return "发送成功";
    }
}
