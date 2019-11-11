package ficheroTexto;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFichero {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket serverAddr = null;
		Socket sc = null;
		
			
		try {
			serverAddr = new ServerSocket(2500); //Se crea el socket
			
		}catch (Exception e){
			System.err.println("Error creando socket");
		}
			
		while (true){

			sc = serverAddr.accept(); // esperando conexión
		
			InputStream istream = sc.getInputStream();
			ObjectInput in = new ObjectInputStream(istream);
			
			String texto = (String) in.readObject();
			
			
			
		}
		
	}
}
