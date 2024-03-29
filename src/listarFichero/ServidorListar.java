package listarFichero;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ServidorListar {

	public static void main(String[] args) {
		ServerSocket serverAddr = null;
		Socket sc = null;
		ArrayList <String> array = new ArrayList<>();
		InputStream istream =null;
		ObjectInput in =null;
		try {
			try {
				serverAddr = new ServerSocket(2500); //Se crea el socket
				sc = serverAddr.accept(); // esperando conexión
				istream = sc.getInputStream();
				in = new ObjectInputStream(istream);
			}catch (Exception e){
				System.err.println("Error creando socket");
			}
				
			while (true){
				try {
					int numero = (Integer) in.readObject();
	
					Path ruta = null;
					ruta = Paths.get("/datos/usuarios/alumnos/jose.guapache/Escritorio");
					
					if (numero == -1) {
						if (ruta.toFile().isDirectory()) {
							String listar [] = ruta.toFile().list();
							for (int i = 0; i < listar.length; i++) {
								array.add(listar [i]);
							}
							
							ObjectOutputStream ostream = new ObjectOutputStream(sc.getOutputStream());
							ostream.writeObject(listar);
							ostream.flush();
							
							
						}
						
					}else {
						System.out.println("Ha introducido mal el número");
					}
				}catch(Exception e) {
					System.err.println("excepcion " + e.toString() );
					e.printStackTrace() ;
				}
					
				try {
					String fichero = (String) in.readObject();					
					if (array.contains(fichero)) {
						File file = new File ("/datos/usuarios/alumnos/jose.guapache/Escritorio/" + fichero);
						
								
						BufferedInputStream bis = new BufferedInputStream (new FileInputStream(file));
						BufferedOutputStream bos = new BufferedOutputStream (sc.getOutputStream());
							
						byte arrayByte []= new byte [1024];
						int tamaño;
								
						while ((tamaño = bis.read(arrayByte)) != -1) {
							bos.write(arrayByte, 0, tamaño);
						}
								
						System.out.println("El fichero se ha copiado");
						bis.close();
						bos.close();
					}
				sc.close();
				}catch(Exception e) {
					System.err.println("excepcion " + e.toString() );
					e.printStackTrace() ;
				}
			}
			
		}catch (Exception e) {
			System.err.println("excepcion " + e.toString() );
			e.printStackTrace() ;
		}
	
			
	}
	
}
