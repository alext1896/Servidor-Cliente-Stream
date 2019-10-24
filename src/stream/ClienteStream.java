package stream;

import java.io.DataInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteStream {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int res;
		int num[] = new int[2];
		
		try {
			// se crea la conexión
			String host = "pc2812";
			Socket sc = new Socket(host, 2500); // conexión
			
			OutputStream ostream = sc.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(ostream);
			DataInputStream istream = new DataInputStream(sc.getInputStream());
			num[0] = 5; num[1] = 2; //prepara la petición
			s.writeObject(num);
			s.flush();
			res = istream.readInt();
			sc.close();
			
			System.out.println("La suma es " + res);
		}catch (Exception e){
			System.err.println("excepcion " + e.toString() );
			e.printStackTrace() ;
		}
	}
}
