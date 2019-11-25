package ahorcado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ServidorAhorcado {

	public static void main(String[] args) {
		ServerSocket serverAddr = null;
		Socket sc = null;
		InputStream istream =null;
		ObjectInput in =null;
		try {
			//SE CREA EL SERVIDOR
			try {
				serverAddr = new ServerSocket(2500); //Se crea el socket
				sc = serverAddr.accept(); // esperando conexión
				istream = sc.getInputStream();
				in = new ObjectInputStream(istream);
			}catch (Exception e){
				System.err.println("Error creando socket");
			}
			
			//EMPIEZAN LOS PROCESOS
			try {
				Path rutaFichero = Paths.get("/Servidor-Cliente Stream/src/ahorcado/palabras");
				Charset charset = Charset.forName("ISO-8859-1");
				BufferedReader br = null;
				String palabras = null;
					try {
						br = Files.newBufferedReader(rutaFichero, charset);
						palabras = br.readLine();
						
						br.close();
						} catch (IOException x) {
							System.err.format("IOException: %s%n", x);
					}
					
					String [] arrayPalabras = new String [palabras.length()];
					arrayPalabras = palabras.split(" ");
					int num = (int) (Math.random() * (palabras.length() - 1) + 1);
					
					String palabra = arrayPalabras [num];
					
					ObjectOutputStream ostream = new ObjectOutputStream(sc.getOutputStream());
					ostream.writeObject(palabra);
					ostream.flush();
					
					
				String texto= (String) in.readObject();

				Path ruta = null;
				ruta = Paths.get("../Servidor-Cliente Stream/src/texto/ficheroCreado");

				if (ruta.toFile().exists()) {
					try (BufferedWriter wr = Files.newBufferedWriter(ruta, charset, StandardOpenOption.APPEND)) {
						wr.write(" " + texto);
						
					}catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					if (ruta.toFile().createNewFile()) {
						try (BufferedWriter wr = Files.newBufferedWriter(ruta, charset, StandardOpenOption.APPEND)) {
								wr.write(texto);
								
							}catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}catch(Exception e) {
					System.err.println("excepcion " + e.toString() );
					e.printStackTrace() ;
				}
		}catch (Exception e) {
			System.err.println("excepcion " + e.toString() );
			e.printStackTrace() ;
		}
		
	}

}
