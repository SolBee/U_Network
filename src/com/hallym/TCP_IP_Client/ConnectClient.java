package com.hallym.TCP_IP_Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConnectClient 
{
	private static final String SERVER_ADDR = "210.115.227.113";
	private static final int SERVER_PORT = 6006;

	private BufferedReader in = null;
	private BufferedReader in2 = null;
	private PrintWriter out = null;

	public ConnectClient()
	{		
	}

	public void startClient()
	{
		Socket client = null;

		in2 = new BufferedReader(new InputStreamReader(System.in));//클라이언트에선 작업하면서 키보드로 입력받아야하니깐System.in

		try
		{
			client = new Socket(SERVER_ADDR, SERVER_PORT);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch(IOException e)
		{
			System.out.println("서버와 연결에 실패하였습니다. 주소나 포트를 확인하세요.");
			return;
		}


		try{
			while(true){
				String msg = in2.readLine();

				out.println(msg);
				out.flush();

				msg = in.readLine();//서버로부터 메세지 받는 것을 기다림

				System.out.println(msg);//서버로부터 받은 메세지 출력
			}
		} catch(IOException e)
		{
			//
		}


		try{
			if( in != null )
				in.close();
			if( out != null)
				out.close();
			if( client != null)
				client.close();	
		} catch(IOException e)
		{
			//
		}


	}
}
