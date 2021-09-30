package AE1;
import java.util.Scanner;
public class AE1_8 {

	public static void IntervaloPrimos(int num1,int num2) {
		boolean primo=true;
		for(int i=num1+1;i<num2;i++) {

			primo=true;
			for(int j=2;j<i;j++) {
				if(i%j==0) {
					primo=false;
					break;
				}
			}
		if(primo) {
			System.out.println(i+"  Primo");
		}else {
			System.out.println(i+"  Compuesto");
		}
		}
		
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Escribe el primer número del intervalo: ");
		try {
		int num1=Integer.parseInt(sc.nextLine());
		System.out.print("Escribe el último número del intervalo: ");
		try {
		int num2=Integer.parseInt(sc.nextLine());
		long startTime = System.nanoTime();
		
		IntervaloPrimos(num1,num2);
		long endTime = System.nanoTime() - startTime;
		System.out.println("Tiempo de ejecución: "+endTime/1e6+" ms.");
		}catch(NumberFormatException e){
			System.out.println("Escriba un número entero");
		}
		}catch(NumberFormatException e) {
			System.out.println("Escriba un número entero");
		}
		
	}
	

}
