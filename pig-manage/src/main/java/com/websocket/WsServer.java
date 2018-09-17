package com.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.security.PrivateKey;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.common.JsonUtils;



public class WsServer extends WebSocketServer {
	
	
    public WsServer(int port) {
        super(new InetSocketAddress(port));
    }

    public WsServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // ws连接的时候触发的代码，onOpen中我们不做任何操作
    	System.out.println();
    	System.out.println("onOpen dp:"+conn.DEFAULT_WSS_PORT+" wssport:"+conn.DEFAULT_WSS_PORT);
    	
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //主动断开连接时候触发代码
    	ClientLeave(conn);
        System.out.println(reason);
    }
    
   

    @Override
	public void onMessage(WebSocket conn, ByteBuffer message) {
		// TODO Auto-generated method stub
		super.onMessage(conn, message);
		
		

	}

	@Override
    public void onMessage(WebSocket conn, String message) {
		System.out.println(message);
		ClientMsg msg = JsonUtils.jsonToPojo(message, ClientMsg.class);
		if (msg.getEventType().equals("online")) { //  上线
			String clientId =  msg.getMsg();
	    	ClientJoin(conn,clientId);
	    	msg.msg = "websocket已连接";
	    	ClientManager.getInstance().sendMessageToClient(clientId, JsonUtils.objectToJson(msg));
		}
    
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
    	//System.out.println("local "+conn.getLocalSocketAddress().toString());
    	//System.out.println("remote "+conn.getRemoteSocketAddress().toString());
        //错误时候触发的代码
        //System.out.println("on error DP:"+conn.DEFAULT_PORT+"WSSport:"+conn.DEFAULT_WSS_PORT+ex.getMessage());
        //ex.printStackTrace();
    }
    /**
     * 去除掉失效的websocket链接
     * @param conn
     */
    private void ClientLeave(WebSocket conn){
        //WsPool.removeClient(conn);
    	//ClientManager.getInstance().
    }
    /**
     * 将websocket加入用户池
     * @param conn
     */
    private void ClientJoin(WebSocket conn,String ClientId){
        //WsPool.addClient(ClientId, conn);
    	ClientManager.getInstance().registClient(new Client(ClientId, conn));
//    	ClientManager.getInstance().sendMessageToClient(ClientId,"websocket已连接");
    	System.out.println(ClientId+"上线");
    }

}