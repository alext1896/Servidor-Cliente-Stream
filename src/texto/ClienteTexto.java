package texto;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTexto {

	public static void main(String[] args) {

//		6.- Realizar una aplicación cliente/servidor que permita al cliente escribir un texto y almacenar ese
//		texto en un fichero en el servidor.
		
		Scanner sc = new Scanner (System.in);
		try {
			// se crea la conexión
			String host = "localhost";
			Socket socket = new Socket(host, 2500); // conexión
					
			OutputStream ostream = socket.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(ostream);
			
			String texto;
			System.out.println("Introduzca el texto que quiere guardar");
			texto = sc.nextLine();
			char [] array = new char [texto.length()];
			
			for (int i = 0; i < array.length; i++) {
				array [i] = texto.charAt(i);
			}
			s.writeObject(texto);
			s.flush();
			
		}catch (Exception e) {
			System.err.println("Excepcion: " + e.toString());
			e.printStackTrace();
		}finally {
			sc.close();
			
		}
		
	}

}
