package com.websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.java_websocket.WebSocket;

public class WsPool {
	
	//
    private static final Map<WebSocket, String> mMapSocketClient = new HashMap<WebSocket, String>();

    /**
     * 通过websocket连接获取其对应的
     * 
     * @param conn
     * @return
     */
    public static String getClientByWs(WebSocket conn) {
        return mMapSocketClient.get(conn);
    }
  
    /**
     * 根据ClientId获取WebSocket,这是一个list,此处取第一个
     * 因为有可能多个websocket对应一个ClientId（但一般是只有一个，因为在close方法中，我们将失效的websocket连接去除了）
     * 
     * @param user
     */
    public static WebSocket getWsById(String ClientId) {
        Set<WebSocket> keySet = mMapSocketClient.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String cuser = mMapSocketClient.get(conn);
                if (cuser.equals(ClientId)) {
                    return conn;
                }
            }
        }
        return null;
    }

    /**
     * 向连接池中添加连接
     * 
     * @param inbound
     */
    public static void addClient(String ClientId, WebSocket conn) {
    	mMapSocketClient.put(conn, ClientId); // 添加连接
    }

    /**
     * 获取所有连接池中的用户，因为set是不允许重复的，所以可以得到无重复的user数组
     * 
     * @return
     */
    public static Collection<String> getOnlineUser() {
        List<String> setClients = new ArrayList<String>();
        Collection<String> setClient = mMapSocketClient.values();
        for (String u : setClient) {
        	setClients.add(u);
        }
        return setClients;
    }

    /**
     * 移除连接池中的连接
     * 
     * @param inbound
     */
    public static boolean removeClient(WebSocket conn) {
    	
    	
        if (mMapSocketClient.containsKey(conn)) {
        	mMapSocketClient.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
    }

    /**
     * 向特定的用户发送数据
     * 
     * @param user
     * @param message
     */
    public static void sendMessageToClient(WebSocket conn, String message) {
        if (null != conn && null != mMapSocketClient.get(conn)) {
            conn.send(message);
        }
    }

    /**
     * 向所有的用户发送消息
     * 
     * @param message
     */
    public static void sendMessageToAll(String message) {
        Set<WebSocket> keySet = mMapSocketClient.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String Client = mMapSocketClient.get(conn);
                if (Client != null) {
                    conn.send(message);
                }
            }
        }
    }
}
