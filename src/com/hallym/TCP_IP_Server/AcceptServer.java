package com.hallym.TCP_IP_Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AcceptServer 
{
	private static final int SERVER_PORT = 6006;

	private BufferedReader in = null;
	private PrintWriter out = null;

	public AcceptServer()
	{		
	}

	public void startServer()
	{
		ServerSocket serverSocket  = null;
		Socket client = null;//���⼭ ���� ���� ���ִ°��� client�� ��� ��, ������ ������ Ŭ���̾�Ʈ�� �ޱ� ����

		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("[SYSTEM] ������ ���Ƚ��ϴ�.");
			System.out.println("[SYSTEM] ���� ���� IP: " + InetAddress.getLocalHost().getHostAddress() + " PORT: "+ SERVER_PORT);			
		} catch (IOException e) {
			System.out.println("�ش� ��Ʈ�� ������Դϴ�. ������ ������ ���α׷����� �������ּ���.");
			return;
		}

		try {
			System.out.println("[SYSTEM] ������ ������ ��ٸ��ϴ�.");
			client = serverSocket.accept();

			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

			System.out.println("[SYSTEM] Ŭ���̾�Ʈ " + client.getInetAddress().getHostAddress()+ " �� �����Ͽ����ϴ�.");

		} catch (IOException e) {
			System.out.println("[SYSTEM] �����κ����� ������ �޴� �Ϳ� ���� ������ �߻��߽��ϴ�.");
			try{
				if( in != null)
					in.close();
				if( out != null)
					out.close();
				if( client != null)
					client.close();

				if( serverSocket != null)
					serverSocket.close();
			}catch(IOException e2)
			{
				//
			}
			return;

		}
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			while(true){
				String input = in.readLine();

				if( input == null )
					System.out.println("[SYSTEM] Ŭ���̾�Ʈ���� ������ ���������ϴ�.");
				else
				{
					System.out.println("[SYSTEM] Ŭ���̾�Ʈ�� �޼����� �޾ҽ��ϴ� : " + input);
					
					if(input.equals("myip"))
					{
						input = client.getInetAddress().getHostAddress();						
					}
					else if(input.equals("time"))
					{
						input = dateFormat.format(calendar.getTime());
					}else
					{
						input = "�߸��� ��ɾ� �Դϴ�.";						
					}							

					out.println(input);
					out.flush();//�ٷ�����

					System.out.println("[SYSTEM] Ŭ���̾�Ʈ���� �޼����� ���½��ϴ� : " + input);

				}
			}
		} catch (IOException e) {
			System.out.println("[SYSTEM] Ŭ���̾�Ʈ���� ������ ���������ϴ�.");
		}

		try{
			if( in != null)
				in.close();
			if( out != null)
				out.close();
			if( client != null)
				client.close();

			if( serverSocket != null)
				serverSocket.close();
		} catch(IOException e)
		{
			//
		}
		System.out.println("[SYSTEM] ������ ����Ǿ����ϴ�.");

	}
}
