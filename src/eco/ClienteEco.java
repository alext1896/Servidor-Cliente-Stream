package eco;

import java.io.DataInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEco {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		String recibir;
		try {
			// se crea la conexión
			String host = "localhost";
			Socket sc = new Socket(host, 2500); // conexión
					
			OutputStream ostream = sc.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(ostream);
			DataInputStream istream = new DataInputStream(sc.getInputStream());
			
			System.out.println("Introduce la palabra para hacer eco");
			String palabra = scanner.nextLine();
			
			s.writeObject(palabra);
			s.flush();
			recibir = istream.readUTF();
			sc.close();
						
			System.out.println("ECO: " + recibir);
		}catch (Exception e) {
			
			System.err.println("Excepcion: " + e.toString());
			e.printStackTrace();
		}finally {
			scanner.close();
		}
	}

}
