package ficheros;

import java.io.DataInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteFichero {

	public static void main(String[] args) {
		//Realizar un cliente que envíe un nombre de fichero a un servidor y el servidor le conteste con
		//“Encontrado” o “No encontrado” dependiendo de si ese fichero se encuentra en el directorio “datos”
		//ubicado en el servidor (el alumno creará ese directorio con algunos ficheros).
		Scanner sc = new Scanner (System.in);
		boolean recibir;
		
		try {
			// se crea la conexión
			String host = "localhost";
			Socket socket = new Socket(host, 2500); // conexión
					
			OutputStream ostream = socket.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(ostream);
			DataInputStream istream = new DataInputStream(socket.getInputStream());
			
			System.out.println("Introduce el nombre del fichero para ver si se encuentra en la carpeta datos");
			String fichero = sc.nextLine();
			
			s.writeObject(fichero);
			s.flush();
			recibir = istream.readBoolean();
			socket.close();
						
			if (recibir == false) {
				System.out.println("El fichero no existe");
			}else {
				System.out.println("El fichero existe");
			}
			
		}catch (Exception e) {
			
			System.err.println("Excepcion: " + e.toString());
			e.printStackTrace();
		}finally {
			sc.close();
		}
	}

}
