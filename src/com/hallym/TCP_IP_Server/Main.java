package com.hallym.TCP_IP_Server;

public class Main 
{
	public static void main(String[] args) 
	{
		AcceptServer server = new AcceptServer();
		server.startServer();
	}
}