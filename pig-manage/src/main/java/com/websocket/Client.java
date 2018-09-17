package com.websocket;

import org.java_websocket.WebSocket;

public class Client {
	
	private String mStrClientId;
	
	private WebSocket mSocket;
	
	
	public Client(String ClientId, WebSocket socket)
	{
		mStrClientId = ClientId;
		mSocket = socket;
	}
	
	public String getClientId()
	{
		return mStrClientId;
	}
	
	public void setWebSocket(WebSocket socket)
	{
		mSocket = socket;
	}
	
	public WebSocket getWebSocket()
	{
		return mSocket;
	}
	
	
	

}
