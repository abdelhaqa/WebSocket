package com.geekrai.websocket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.glassfish.tyrus.server.Server;

/**
 * Implements server using tyrus server API
 * Deploys the server end point on port 8025
 * 
 * @author Siddheshwar
 *
 */
public class WebSocketServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Server server = new Server("localhost", 8025, "/websockets", ServerWSEndpoint.class);
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Press any key to stop server :");
            reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
