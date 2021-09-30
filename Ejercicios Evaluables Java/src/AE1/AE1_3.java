package AE1;
import java.util.Scanner;
public class AE1_3 {

	public static int SumaPares(int num){
		int contador=0;
		for(int i=2;i<=num;i++) {
			if(i%2==0) {
				contador+=i;
			}
		}
		return contador;
	}
	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		System.out.print("Escribe el numero hasta el que quieras sumar: ");
		int num=Integer.parseInt(teclado.nextLine()) ;
		System.out.println(SumaPares(num));
	}

}
