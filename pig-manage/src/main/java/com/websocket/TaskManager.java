package com.websocket;

import java.util.HashMap;
import java.util.Map;



public class TaskManager {
private Map<String, String> taskMap = new HashMap<String, String>();
static private TaskManager mInstance = new TaskManager();
private TaskManager(){}
public static  TaskManager getInstance()
{
	if(mInstance == null)
	{
		synchronized (TaskManager.class) {
			if(mInstance == null)
			{
				mInstance = new TaskManager();
			}
		}
	}
	return mInstance;	
}


//key 为 taskId  value 为 ClientId  

 public String getClientIdByTaskId(String taskId){
return	taskMap.get(taskId);	
}

 public void addTask(String taskId,String ClientId){
	taskMap.put(taskId, ClientId);
}
 public void compTask(String taskId){
	taskMap.remove(taskId);
}

}
