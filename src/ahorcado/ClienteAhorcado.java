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
		Scanner sc = new Scanner(System.in);
		String host = "localhost";
		Socket socket = new Socket(host, 2500); // conexión

		String palabra;
		OutputStream ostream = socket.getOutputStream();
		ObjectOutput s = new ObjectOutputStream(ostream);
		// Recibimos la palabra del servidor
		ObjectInputStream istream = new ObjectInputStream(socket.getInputStream());
		palabra = (String) istream.readObject();

		// Creamos el panel con el número de letras que tiene la palabra
		System.out.println("El juego va a terminar cuando tengas 7 errores");
		int errores = 0;
//		boolean habia = true;
		espacios(3);
		for (int i = 0; i < palabra.length(); i++) {
			System.out.print("_" + " ");
		}
		espacios(2);
		boolean terminado = false;
		
		while (terminado == false) {
			System.out.println("Introduce la letra que crees que está. Número de errores =" + errores);
			String letra = sc.nextLine();

			s.writeObject(letra);
			s.flush();
			String imprimir;
			imprimir = (String) istream.readObject();
			System.out.print(imprimir);
			espacios (1);
			
		}
		
		sc.close();
	}

	private static void espacios(int numLineas) {
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
