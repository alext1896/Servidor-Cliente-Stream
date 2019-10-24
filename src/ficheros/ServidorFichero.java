package ficheros;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorFichero {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ServerSocket serverAddr = null;
		Socket sc = null;
		
		String nombreFichero;
			
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
			
				nombreFichero = (String) in.readObject();
				
				Path ruta = null;
				
				ruta = Paths.get("/datos/" + nombreFichero);
				boolean existe;
				
				if (ruta.toFile().exists()) {
					existe = true;
				}else {
					existe = false;
				}
			
				DataOutputStream ostream = new DataOutputStream(sc.getOutputStream());
		
				ostream.writeBoolean(existe);
				ostream.flush();
					
				sc.close();
			}catch(Exception e) {
				System.err.println("excepcion " + e.toString() );
				e.printStackTrace() ;
			}
		}
	}

}
