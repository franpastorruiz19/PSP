package AE1;
import java.util.Scanner;
public class AE1_6 {

	public static int Ejercicio() {
		int array[]=new int[5];
		int suma=0;
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<5;i++) {
		System.out.print("Escribe un número: ");	
		array[i]=Integer.parseInt(sc.nextLine());		
		suma+=array[i];
		}
		System.out.println("Salida por teclado de modo inverso");
		for(int j=4;j>=0;j--) {
			System.out.print(array[j]+" ");
		}
		System.out.println();
		System.out.println("Suma");
		return suma;
		
	}
	public static void main(String[] args) {
		
		System.out.println(Ejercicio());
		
	}

}
