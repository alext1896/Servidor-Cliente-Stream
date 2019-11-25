package ahorcado;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteAhorcado {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// se crea la conexión
		Scanner sc = new Scanner (System.in);
		String host = "localhost";
		Socket socket = new Socket(host, 2500); // conexión
		
		String palabra;
		OutputStream ostream = socket.getOutputStream();
		ObjectOutput s = new ObjectOutputStream(ostream);
		
		ObjectInputStream istream = new ObjectInputStream(socket.getInputStream());
		palabra = (String) istream.readObject();
		//Recibimos la palabra y creamos el panel con el número de letras que tiene la palabra
		
		espacios (3);
		for (int i = 0; i < palabra.length(); i++) {
			System.out.print("_" + " ");
		}
		
		System.out.println("Introduce la letra que crees que está");
		String letra = sc.nextLine();
		
		if (letra.length() < 1) {
			if (palabra.contains(letra)) {
				for (int i = 0; i < palabra.length(); i++) {
					System.out.print("_" + " ");
				}
			}
		}

	}
	
	private static void espacios (int numLineas) {
		for (int i = 0; i < numLineas; i++) {
			System.out.println();
		}
	}
	
//	private static void muñeco (boolean acierto) {
//		if (acierto) {
//			
//		}
//		
//		
//		
//	}

}
