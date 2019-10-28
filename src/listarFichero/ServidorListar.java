package listarFichero;

import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorListar {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ServerSocket serverAddr = null;
		Socket sc = null;
		
			
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
				
				int numero = (Integer) in.readObject();
				Path ruta = null;
				ruta = Paths.get("/datos/usuarios/alumnos/jose.guapache/Escritorio");
				
//				if (numero == -1) {
					if (ruta.toFile().isDirectory()) {
						String listar [] = ruta.toFile().list();
						
						ObjectOutputStream ostream = new ObjectOutputStream(sc.getOutputStream());
						
						ostream.writeObject(listar);
						
						ostream.flush();
						sc.close();
						
					}else {
						System.out.println("La ruta no existe");
					}
//				}else {
//					String listar [] = ruta.toFile().list();
//					
//					
//				}
				
				
				
			}catch(Exception e) {
				System.err.println("excepcion " + e.toString() );
				e.printStackTrace() ;
			}
		}
	}

}
