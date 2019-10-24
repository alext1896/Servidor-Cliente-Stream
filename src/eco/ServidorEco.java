package eco;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEco {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ServerSocket serverAddr = null;
		Socket sc = null;
		
		String mandar;
			
		try {
			serverAddr = new ServerSocket(2500); //Se crea el socket
			
		}catch (Exception e){
			System.err.println("Error creando socket");
		}
			
		while (true){
			try {
				sc = serverAddr.accept(); // esperando conexión
			
				InputStream istream = sc.getInputStream();
				ObjectInput in = new ObjectInputStream(istream);
			
				mandar = (String) in.readObject();
			
				DataOutputStream ostream = new DataOutputStream(sc.getOutputStream());
		
				ostream.writeUTF(mandar);
				ostream.flush();
					
				sc.close();
			}catch(Exception e) {
				System.err.println("excepcion " + e.toString() );
				e.printStackTrace() ;
			}
		}
	}
}
