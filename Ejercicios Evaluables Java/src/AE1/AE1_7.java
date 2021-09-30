package AE1;

import java.util.Scanner;

public class AE1_7 {

	static boolean ControlError(double num) {
		boolean ok = true;
		if (num < 0) {
			ok = false;
		}
		return ok;
	}

	public static void NivelySalario() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Escribe tu nombre: ");
		String nom = sc.nextLine();
		System.out.print("Escribe tus años de antiguedad: ");
		try {
			double anyo = Double.parseDouble(sc.nextLine());

			if (ControlError(anyo)) {
				if (anyo < 1) {
					System.out.println("Desarrollador Junior L1 – 15000-18000");
				} else if (anyo >= 1 && anyo < 3) {
					System.out.println("Desarrollador Junior L2 – 18000-22000");
				} else if (anyo >= 3 && anyo <= 5) {
					System.out.println("Desarrollador Senior L1 – 22000-28000");
				} else if (anyo >= 5 && anyo <= 8) {
					System.out.println("Desarrollador Senior L2 – 28000-36000");
				} else {
					System.out.println("Analista / Arquitecto. Salario a convenir en base a rol");
				}

			} else {
				System.out.println("El número escrito no és válido");
			}

		} catch (NumberFormatException e) {
			System.out.println("No ha escrito un número");
		}

	}

	public static void main(String[] args) {
		NivelySalario();
	}

}
