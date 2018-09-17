package com.websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.java_websocket.WebSocket;

public class ClientManager {
	
	//保存socket到客户端的关系，方便通过socket快速找到客户端
	private Map<WebSocket,Client> mMapSocketToClient;
	//保存机器人id到客户端的关系
	private Map<String,Client> mMapIdToClient;
	
	private Map<String,Thread> mWorkThread;
	//单例
	private static ClientManager mInstance;

	private ExecutorService mCachedThreadPool;
	
	public static  ClientManager getInstance()
	{
		if(mInstance == null)
		{
			synchronized (ClientManager.class) {
				if(mInstance == null)
				{
					mInstance = new ClientManager();
				}
			}
		}
		return mInstance;	
	}
	
	
	
	private ClientManager()
	{
		mCachedThreadPool = Executors.newFixedThreadPool(2);
		//mListClients = new HashMap<String,Thread>();
		mMapSocketToClient = new HashMap<WebSocket,Client>();
		mMapIdToClient = new HashMap<String,Client>();
	}
	
	public boolean removeClient(WebSocket conn)
	{
		if (mMapSocketToClient.containsKey(conn)) {
			Client Client = mMapSocketToClient.get(conn);
			mMapIdToClient.remove(Client.getClientId());
			mMapSocketToClient.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
	}
	
	/**
	 * 注册客户端
	 * 
	 * @param Client
	 */
	public void registClient(Client Client)
	{
		//创建工作线程
		mMapSocketToClient.put(Client.getWebSocket(), Client);
		mMapIdToClient.put(Client.getClientId(), Client);
		
		//mListClients.put(Client.getClientId(), value)
		//mListClients.put(Client.getClientId(), Client);
	}
	
	/**
	 * 发送消息给指定的socket
	 * 
	 * @param conn
	 * @param message
	 */
	public void sendMessageToClient(WebSocket conn, String message) {
        if (null != conn && null != mMapSocketToClient.get(conn)) {
            conn.send(message);
        }
    }
	

	
	/**
	 * 发送消息给指定客户端
	 * 
	 * @param ClientId
	 * @param message
	 */
	public void sendMessageToClient(String ClientId, String message) {
        if (!ClientId.isEmpty() && null != mMapIdToClient.get(ClientId)) {
        	Client Client = mMapIdToClient.get(ClientId);
        	Client.getWebSocket().send(message);
        }
    }
	
	/**
	 * 获得在线客户端id列表
	 */
	public List<String> getOnlineClient() {
        List<String> setClients = new ArrayList<String>();
        Collection<Client> setClient = mMapSocketToClient.values();
        for (Client Client : setClient) {
        	setClients.add(Client.getClientId());
        }
        return setClients;
    }
	
	
	/**
     * 向所有的用户发送消息
     * 
     * @param message
     */
    public void sendMessageToAll(String message) {
        Set<WebSocket> keySet = mMapSocketToClient.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                Client Client = mMapSocketToClient.get(conn);
                if (Client != null) {
                    conn.send(message);
                }
            }
        }
    }
	
}
