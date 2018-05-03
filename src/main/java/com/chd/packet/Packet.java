package com.chd.packet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Packet {
//	private static final int port = 8888;
//	private static final String host = "localhost";
	
	private static final int port = 5053;
	private static final String host = "118.178.213.202";
	
	public static byte[] HexStringToByteArray(String hexString) {
		byte[] bytes = new byte[hexString.length() / 2];
		for (int i = 0; i < hexString.length() / 2; i++) {
			String temp = hexString.substring(i * 2, (i + 1) * 2);
			byte v = (byte) Integer.parseInt(temp, 16);
			bytes[i] = v;
		}
		return bytes;
	}
	
	public static void main(String[] args) {
		String hexData = "1401f512cb85ea5ae01c0100b80087ab6d5f6c4d73674f72646572d10250a96d5f694d6435456e6445ab6d5f694d6435426567696e3dae6d5f7374725665726966794b6579d92026334b6754266e4d352948322a4e744f2634337c62413556793f497c34367329a76d5f6d73674964d101f4a86d5f7374724d6435d9203265393834363436303966333264373330336366633733623231653864303365ab6d5f737472557365724964bc6f664a62387772774753735676724d516b5933585a3657536f684e5158000184ad6d5f616363657373546f6b656ed92430623036626634392d343136642d356132662d386461342d633164313635363237643036a76d5f6d7367496408a86d5f757365724964d2034332a1a56d5f676964d2034332a1";
		byte[] byteArray = HexStringToByteArray(hexData);
		try {
			Socket socket = new Socket(host, port);
			OutputStream outputStream = socket.getOutputStream();	
			outputStream.write(byteArray);
			InputStream inputStream = socket.getInputStream();
			int length = 0;
			byte[] outputArray = new byte[1024];
			ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
			while((length = inputStream.read(outputArray)) != -1) {
				byteArrayInputStream.write(outputArray, 0, length);
			}
			byte[] array = byteArrayInputStream.toByteArray();
			System.out.println(Arrays.toString(array));
			System.out.println(new String(array,"utf-8"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String filePath = "C:\\Users\\jumili\\Desktop\\熊猫麻将";
//		readHexDatas(filePath);
	}
	
	public static void shuihuniuniu() {
		String C2S_Version = "001092ab4332535f56657273696f6ec40190";
		String C2S_Register = "00a492ac4332535f5265676973746572c49493bc6f41534635316246696432586262577a58326932474e4c72474e694dd96d395f494d4f464a35454d6e71734a424f49413035392d54536e48744977616664585265705238616841726e67395354576d65537365336d38533676317641764d3473565a4f557254625251624d3353644770496e6b4135526f755449524b567146437165487755647035494b59a6312e302e3133";
		byte[] C2S_Version_Data = HexStringToByteArray(C2S_Version);
		byte[] C2S_Register_Data = HexStringToByteArray(C2S_Register);
		
		try {
			Socket socket = new Socket(host, port);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(C2S_Register_Data);
			
			InputStream inputStream = socket.getInputStream();
			
			int length = 0;
			while((length = inputStream.read(C2S_Register_Data)) != -1) {
				System.out.println(new String(C2S_Register_Data,0,length));
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[] readHexDatas(String filePath) {
		String[] hexDatas = null;
		try {
			File file = new File(filePath);
			FileInputStream input = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String hexData = "";
			try {
				while((hexData = br.readLine()) != null) {
					System.out.println(hexData);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
