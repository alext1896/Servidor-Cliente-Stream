package listarFichero;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteListar {

	public static void main(String[] args) {
		
		//EJERCICIO 4
//		Realizar un servidor que cuando un cliente le envíe el número 1, el servidor le devuelva la lista
//		de ficheros que contiene el directorio “datos” (el alumno creará ese directorio con algunos ficheros).
//		El cliente muestra por pantalla los datos recibidos.
		
		//EJERCICIO 5
//		Añadir al ejercicio anterior la siguiente funcionalidad: el cliente una vez mostrada por pantalla la
//		información de los ficheros, permite al usuario seleccionar uno de ellos y le envía al servidor la
//		petición de obtener ese fichero. El servidor le enviará el contenido de ese fichero. El cliente recibirá
//		ese fichero y lo almacenará en el directorio “recibidos” (se supone ya creado por el alumno).
		
		Scanner sc = new Scanner (System.in);
		String [] recibir;
		String fichero = null;
		try {
			// se crea la conexión
			String host = "localhost";
			Socket socket = new Socket(host, 2500); // conexión
					
			OutputStream ostream = socket.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(ostream);
			
			int num;
		
			System.out.println("Introduzca -1 para listar la carpeta Escritorio o 100 para finalizar el programa");
			num = sc.nextInt();
			sc.nextLine();
			
				
			s.writeObject(num);
			s.flush();
			ObjectInputStream istream = new ObjectInputStream(socket.getInputStream());
			recibir = (String[]) istream.readObject();
						
			for (int i = 0; i < recibir.length; i++) {
				System.out.println(i + 1 + " - " + recibir[i]);
			}
			
			System.out.println("Introduce el nombre del archivo que quieres descargar");
			fichero = sc.nextLine();
			s.writeObject(fichero);
			s.flush();
			
			FileOutputStream fosFichero = new FileOutputStream ("recibidos/"+fichero);
			InputStream istreamFichero = socket.getInputStream();
			
			byte arrayByte []= new byte [1024];
			int tamaño;
			
			while ((tamaño = istreamFichero.read(arrayByte)) != -1) {
				fosFichero.write(arrayByte, 0, tamaño);
			}
				
			System.out.println("El fichero se ha copiado");
			fosFichero.close();
			socket.close();

		}catch (Exception e) {
			
			System.err.println("Excepcion: " + e.toString());
			e.printStackTrace();
		}finally {
			sc.close();
			
		}
	}

}














