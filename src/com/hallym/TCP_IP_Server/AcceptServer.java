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
		Socket client = null;//여기서 소켓 생성 해주는것은 client라 명명 후, 서버에 접속한 클라이언트를 받기 위함

		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("[SYSTEM] 서버가 열렸습니다.");
			System.out.println("[SYSTEM] 서버 정보 IP: " + InetAddress.getLocalHost().getHostAddress() + " PORT: "+ SERVER_PORT);			
		} catch (IOException e) {
			System.out.println("해당 포트가 사용중입니다. 이전에 실행한 프로그램들을 종료해주세요.");
			return;
		}

		try {
			System.out.println("[SYSTEM] 서버의 접속을 기다립니다.");
			client = serverSocket.accept();

			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

			System.out.println("[SYSTEM] 클라이언트 " + client.getInetAddress().getHostAddress()+ " 가 접속하였습니다.");

		} catch (IOException e) {
			System.out.println("[SYSTEM] 서버로부터의 접속을 받는 것에 대해 문제가 발생했습니다.");
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
					System.out.println("[SYSTEM] 클라이언트와의 접속이 끊어졌습니다.");
				else
				{
					System.out.println("[SYSTEM] 클라이언트가 메세지를 받았습니다 : " + input);
					
					if(input.equals("myip"))
					{
						input = client.getInetAddress().getHostAddress();						
					}
					else if(input.equals("time"))
					{
						input = dateFormat.format(calendar.getTime());
					}else
					{
						input = "잘못된 명령어 입니다.";						
					}							

					out.println(input);
					out.flush();//바로전송

					System.out.println("[SYSTEM] 클라이언트에게 메세지를 보냈습니다 : " + input);

				}
			}
		} catch (IOException e) {
			System.out.println("[SYSTEM] 클라이언트와의 접속이 끊어졌습니다.");
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
		System.out.println("[SYSTEM] 서버가 종료되었습니다.");

	}
}
