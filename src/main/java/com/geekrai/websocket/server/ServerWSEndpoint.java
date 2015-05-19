package com.geekrai.websocket.server;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Service End point implementation, accessible through url ws://localhost:8025/websockets/helloWebSocket
 * @author Siddheshwar
 *
 */
@ServerEndpoint(value = "/helloWebSocket")
public class ServerWSEndpoint {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }

    @OnMessage
    public String onMessage(String text, Session session) {       
    	if(text.equals("quit")){
    		try {
                session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "session finished"));
                logger.info("Session closed as requested by client !");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    	}
    	return text + " from server " ;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }

}
