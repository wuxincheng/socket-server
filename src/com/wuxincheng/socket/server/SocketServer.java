package com.wuxincheng.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket·þÎñÆ÷Ä£Äâ
 */
public class SocketServer {

	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.start();
	}

	private void start() {
		ServerSocket serverSocket = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("socket server started...");
			socket = serverSocket.accept();
			System.out.println("client " + socket.hashCode() + " connected");
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String receivedMessage;
			while ((receivedMessage = reader.readLine()) != null) {
				System.out.println("received message: " + receivedMessage);
				writer.write("server reply message is " + receivedMessage + "\n");
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				serverSocket.close();
				writer.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
