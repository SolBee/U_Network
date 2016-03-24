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

		in2 = new BufferedReader(new InputStreamReader(System.in));//Ŭ���̾�Ʈ���� �۾��ϸ鼭 Ű����� �Է¹޾ƾ��ϴϱ�System.in

		try
		{
			client = new Socket(SERVER_ADDR, SERVER_PORT);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch(IOException e)
		{
			System.out.println("������ ���ῡ �����Ͽ����ϴ�. �ּҳ� ��Ʈ�� Ȯ���ϼ���.");
			return;
		}


		try{
			while(true){
				String msg = in2.readLine();

				out.println(msg);
				out.flush();

				msg = in.readLine();//�����κ��� �޼��� �޴� ���� ��ٸ�

				System.out.println(msg);//�����κ��� ���� �޼��� ���
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
