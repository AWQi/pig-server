package com.port;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.TooManyListenersException;

import org.apache.commons.lang3.ArrayUtils;

import com.alibaba.druid.sql.visitor.functions.Char;




public class PortUtil implements SerialPortEventListener {
	static public final String rfidConflict = "0200000447044F03";
	static public final String rfidFind= "0200000446529C03";

    private static final String DEMONAME = "串口测试";

    /**
     * 检测系统中可用的端口
     */
    private CommPortIdentifier portId;
   
    private static InputStream inputStream;
    
    private static OutputStream outputStream;
 
    //RS-232的串行口
    private SerialPort serialPort;

    /**
     * 初始化
     * @param baudRate 波特率
     */
    public void init(int baudRate,String port) {
    	
    	try {
//    		portId=CommPortIdentifier.getPortIdentifier("COM8");
    		
    	
			portId=CommPortIdentifier.getPortIdentifier(port);
			
        	System.out.println("打开端口："+portId.getName());
            serialPort = (SerialPort) portId.open(DEMONAME,2000);
            //设置串口监听
            serialPort.addEventListener(this);
            //设置开启监听
            serialPort.notifyOnDataAvailable(true);
            //设置波特率、数据位、停止位、检验位
            serialPort.setSerialPortParams(baudRate,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //获取输入流
            inputStream = serialPort.getInputStream();
            outputStream=serialPort.getOutputStream();
        } catch (PortInUseException e) {
        	System.out.println("端口被占用");
            e.printStackTrace();
        } catch (TooManyListenersException e) {
        	System.out.println("串口监听类数量过多！添加操作失败！");
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("输入出错");
            e.printStackTrace();
        } catch (NoSuchPortException e) {
        	System.out.println("没有该端口");
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("尝试重新连接串口");
//			init(baudRate, port);
		}
    }

    /**
     * 监听函数
     */
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
         
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
        	break;
       //获取到有效信息
        case SerialPortEvent.DATA_AVAILABLE :            	
        	                
        	break;  
        	
        default:               
        	break;       
        }
    }

    /**
     * 读取串口serialEvent信息
     */
    public ZigbeeDate readZigbeePort() {
    	byte[] readBuffer = new byte[11];
        int availableBytes  = 0;
       
        try {
	
				availableBytes = inputStream.available();
				while (availableBytes > 0) {
					inputStream.read(readBuffer);
					
					System.out.println(byte2HexStr(readBuffer));
					
					/**
					 * 转化为string 时
					 * 0-9 数据帧首部
					 * 10-13  网络地址
					 * 14-15 数据类型    01 温度 02 湿度
					 * 16-19 数据 
					 * 20-21 帧尾校验 
//					 */
					String[]  ss = byte2HexStr(readBuffer).split(" ");
					String address=ss[6]+ss[5]; // 网络地址  获取数据与 串口助手显示 两个byte颠倒
					String type=ss[7]; // 数据类型  01 温度 02 湿度
					
					float date =Integer.parseInt(ss[9]+ss[8],16)/100.0f;// 数据 获取数据与 串口助手显示 两个byte颠倒  在由小数点分开
							
					System.out.println(date);
					return new ZigbeeDate(address, type, date);
					
				}
				Thread.sleep(0);
	
        }catch (IOException e) {
        	System.out.println("获取输出流失败");
        	System.exit(0);
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
        return null;
        }
    
    
  static public int hexStr2Int(String s){
    	char[] cs =s.toCharArray();
    	int[] is = new int[s.length()];
    	for(int i =0 ;i<cs.length;i++) {
    		is[i] = cs[i];
    	}
    	for(int i = cs.length-1;i>=0;i--) {
    		for(int j = i;j>=0;j--) {
    			is[j]*=16;
    		}
    	}
    	int sum = 0;
    	for (int i = 0; i < is.length; i++) {
			sum+=is[i];
		}
    	return sum;
    }
  //向串口发送信息方法
  	public void sendMsg(String msg){
  		
  		String info="";
//  		String msg = "071800F100A00102";//要发送的命令
  		info="02"+msg+checkcode(msg);
  		System.out.println("info="+info+"  字符串："+hexStr2Bytes(info));

  		try {
  		    outputStream.write(hexStr2Bytes(info));
  		    outputStream.flush();
  		    System.out.println("输出成功");
  		} catch (IOException e) {
  			e.printStackTrace();
  			System.out.println("输出失败");
  		}
  	}
  	


    /*
	 *bytes字符串转换为Byte值 
     * @param String src Byte字符串，每个Byte之间没有分隔符 
     * @return byte[] 
     */  
    public static byte[] hexStr2Bytes(String src)  
    {  
    	/* 对输入值进行规范化整理 */
		src = src.trim().replace(" ", "").toUpperCase();
		
        int m=0,n=0;  
        int l=src.length()/2;  
        System.out.println(l);  
        byte[] ret = new byte[l];  
        for (int i = 0; i < l; i++)  
        {  
            m=i*2+1;  
            n=m+1;  
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m)+ src.substring(m, n)) & 0xFF);
        }  
        return ret;  
    }  
    
  //字节转换成十六进制字符串
    public static String bytesToHexString(byte[] src){  
        StringBuilder stringBuilder = new StringBuilder("");  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                stringBuilder.append(0);  
            }  
            stringBuilder.append(hv);  
        }  
        return stringBuilder.toString();  
    }
    
    

//bytes转换成十六进制字符串 ,每个Byte值之间空格分隔 
    public static String byte2HexStr(byte[] b)  

    {  
        String stmp="";  

        StringBuilder sb = new StringBuilder("");  

        for (int n=0;n<b.length;n++)  
        {  
            stmp = Integer.toHexString(b[n] & 0xFF);  

            sb.append((stmp.length()==1)? "0"+stmp : stmp);  

//            sb.append("");  
            sb.append(" ");  
        }  
        return sb.toString().toUpperCase().trim();  
    }  


 
    // 关闭串口
 	public void closeSerialPort() {
 		if (serialPort != null) {
 			
 			serialPort.notifyOnDataAvailable(false);
 			serialPort.removeEventListener();
 			
 			if (inputStream != null) {
 				try {
 					inputStream.close();
 					inputStream = null;

 				}
 				catch (IOException e) {
 					System.out.println("hhh");
 				}
 			}
 			if (outputStream != null) {
 				try {
 					outputStream.close();
 					outputStream = null;
 				}
 				catch (IOException e) {}
 			}
 			serialPort.close();
 			serialPort = null;
 		}
 	}
 	

private static String Xor(String strHex_X,String strHex_Y){ 

			//将x、y转成二进制形式 

			String anotherBinary=Integer.toBinaryString(Integer.valueOf(strHex_X,16)); 

			String thisBinary=Integer.toBinaryString(Integer.valueOf(strHex_Y,16)); 

			String result = ""; 

			//判断是否为8位二进制，否则左补零 
			if(anotherBinary.length() != 8){ 
			for (int i = anotherBinary.length(); i <8; i++) { 

					anotherBinary = "0"+anotherBinary; 
				} 
			} 
			if(thisBinary.length() != 8){ 
			for (int i = thisBinary.length(); i <8; i++) { 
					thisBinary = "0"+thisBinary; 
				} 
			} 

			//异或运算 
			for(int i=0;i<anotherBinary.length();i++){ 
			//如果相同位置数相同，则补0，否则补1 
					if(thisBinary.charAt(i)==anotherBinary.charAt(i)) 
						result+="0"; 
					else{ 
						result+="1"; 
					} 
				}
			return Integer.toHexString(Integer.parseInt(result, 2)); 
		}


public static String checkcode(String para) {  
    int length = para.length() / 2;  
    String[] dateArr = new String[length];  

    for (int i = 0; i < length; i++) {  
        dateArr[i] = para.substring(i * 2, i * 2 + 2);  
    }  
    String code = "00";  
    for (int i = 0; i < dateArr.length; i++) {  
        code = Xor(code, dateArr[i]);  
    }  
    return code;  
} 

public double exchange(String string){
	String a=string.substring(0,2);
	String b=string.substring(2,4);
	String temp="";
	
	temp=a;
	a=b;
	b=temp;
	
	return Integer.parseInt(a+b,16)/100.00;
}
/**
 * 
 * 
 * 
 *  		RFID    数据处理
 * 
 * 
 * 
 * @param msg
 */

//向串口发送rfid校验
	public void sendRFIDMsg(String msg){
		

//		System.out.println(" RFID帧数据="+msg+"  ："+msg.getBytes());

		try {
		    outputStream.write(hexStr2Bytes(msg));
		    outputStream.flush();
//		    System.out.println("输出成功");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("输出失败");
		}
	}

    
    public  String readRfidPort() {
//    	SerialPort serialPort
		InputStream in = null;
		byte[] bytes = {};
		try {
			in = serialPort.getInputStream();
			// 缓冲区大小为一个字节
			byte[] readBuffer = new byte[1];
			int bytesNum = in.read(readBuffer);
			while (bytesNum > 0) {
				bytes = ArrayUtils.addAll(bytes, readBuffer);
//				bytes = ArrayUtils.concat(bytes, readBuffer);
				bytesNum = in.read(readBuffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bytesToHexString(bytes);
	}
    public String checkInfo(String s){
		if (s.length()>20) {
			return s.substring(10, 20);
		}
    	
    	return null;
    }
    

    
     public static void main(String[] args)  {
//    	 
//    	PortUtil test1=new PortUtil();
////    	test1.closeSerialPort();	
////    	test1.init(115200,"COM7");
//    	test1.init(19200,"COM8");
//	
////    	test1.sendMsg(rfid);
//    	test1.sendRFIDMsg(rfidFind);
//    	String s = test1.readFromPort() ; 	
//    	System.out.println("获取数据："+s);
//    	String info = test1.checkInfo(s);
//
//    	if (info!=null) {
//			System.out.println("info"+info);
//		}
//    	test1.sendRFIDMsg(rfidConflict);
//    	 s = test1.readFromPort() ; 	
//    	System.out.println("获取数据"+s);
//    	 info = test1.checkInfo(s);
//
//    	if (info!=null) {
//			System.out.println("info  "+info);
//		}
//   	
//    	
////    	test1.sendRFIDMsg(rfidConflict);
////    	Thread.sleep(1000);
////    	test1.readRFIDPort();
////    	test1.readFromPort();
////        test1.closeSerialPort();
    	 
    	 
   	 
    	PortUtil test1=new PortUtil();
    	test1.init(115200,"COM7");
    	while(true) {
    		test1.readZigbeePort();
    	}	
//    	System.out.println("获取数据："+s);
    	 
    	 
    	 
    	

    	
    }
}
 