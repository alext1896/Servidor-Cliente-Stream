package ficheroTexto;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteFichero {

	public static void main(String[] args) {

//		Realizar una aplicación cliente/servidor que permita al cliente escribir un texto y almacenar ese
//		texto en un fichero en el servidor
		
		String fichero = null;
		try {
			// se crea la conexión
			String host = "localhost";
			Socket socket = new Socket(host, 2500); // conexión
					
			OutputStream ostream = socket.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(ostream);
		}catch (Exception e) {
			System.err.println("excepcion " + e.toString() );
			e.printStackTrace() ;
		}
	}

}
