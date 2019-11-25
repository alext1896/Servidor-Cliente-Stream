package ahorcado;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 6;
		
		espacios (1);
		for (int i = 0; i < num; i++) {

			System.out.print("_" + " ");
		}
	}
	
	private static void espacios (int numLineas) {
		for (int i = 0; i < numLineas; i++) {
			System.out.println();
		}
	}
}
