package com.hallym.TCP_IP_Client;

import com.hallym.TCP_IP_Server.AcceptServer;

public class Main 
{
	public static void main(String[] args) 
	{
		ConnectClient client = new ConnectClient();
		client.startClient();
	}
}
