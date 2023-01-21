package clientSimpleSample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MainClass {

	final static String serverIP = "localhost"; //The ip direction (localhost if it's the same machine"
	final static int serverPort = 7878;

	public static void main(String[] args) {
		
		

		for (int i=0; i<100;i++) {
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					
					try {
						sendOrder (InetAddress.getByName(serverIP), serverPort, "hi from client" );
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}).start();
		}


	}
	
	private static void sendOrder (InetAddress inetAddress, int port, String order) throws IOException {
		Socket s = new Socket (inetAddress, port);
		
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		dos.writeUTF(order);
		
		DataInputStream dis = new DataInputStream(s.getInputStream());

		System.out.println( dis.readUTF());
		
		dos.close();
		dis.close();
		s.close();
		
	}


}
