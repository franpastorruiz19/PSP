package AE1;

public class AE1_5 {
	public static int Mayor(int[] array) {
		int mayor = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > mayor) {
				mayor = array[i];
			}
		}
		return mayor;
	}

	public static void main(String[] args) {

		int[] array= {6,7,14,2,12,1};
		System.out.println(Mayor(array));
		
	}

}
