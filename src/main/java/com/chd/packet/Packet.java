package com.chd.packet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Packet {
	public static void main(String[] args) {

		String a = "001092ab4332535f56657273696f6ec40190";
		byte[] bytes = new byte[a.length() / 2];
		for (int i = 0; i < a.length() / 2; i++) {
			String temp = a.substring(i * 2, (i + 1) * 2);
			byte v = (byte) Integer.parseInt(temp, 16);
			bytes[i] = v;
		}

		int port = 8888;
		String host = "localhost";
		
		try {
			Socket socket = new Socket(host, port);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(bytes);
			
			InputStream inputStream = socket.getInputStream();
			
			int length = 0;
			while((length = inputStream.read(bytes)) != -1) {
				System.out.println(new String(bytes,0,length));
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
