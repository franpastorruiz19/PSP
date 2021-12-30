package AE4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] arg) throws UnknownHostException, IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		System.out.println("CLIENTE >> Arranca cliente");
		Socket cliente = new Socket("localhost", 1234);
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Contraseña c = (Contraseña) inObjeto.readObject();
		System.out.println("CLIENTE>> Recibo de servidor la contraseña para que la rellene ");
		System.out.print("Escribe la contraseña: ");
		c.setContraseña(sc.nextLine());
		boolean salir = false;
		int tipo = 0;
		System.out.println("1- Encriptación Basica");
		System.out.println("2- Encriptación MD5");
		do {
			System.out.print("Escribe El tipo de encriptacion que quieres (1..2): ");
			try {
				tipo = Integer.parseInt(sc.nextLine());
				if ((tipo >= 1 && tipo <= 2)) {
					salir = true;
				}
			} catch (Exception e) {
				salir = false;
			}

		} while (!salir);
		c.setTipoContraseña(tipo);
		ObjectOutputStream pMod = new ObjectOutputStream(cliente.getOutputStream());
		pMod.writeObject(c);
		System.out.println("CLIENTE >> Envio al servidor: " + c.contraseña + " para que sea encriptada");
		inObjeto = new ObjectInputStream(cliente.getInputStream());
		c = (Contraseña) inObjeto.readObject();
		System.out.println("CLIENTE>> Recibo de servidor la contraseña encriptada: " + c.getEncriptada());
		System.out.println("Contraseña: " + c.getContraseña());
		if(c.getTipoContraseña()==1) {
			System.out.println("Tipo de encriptación: Básica ");
		}else {
			System.out.println("Tipo de encriptación: MD5 ");
		}
		System.out.println("Contraseña encriptada: " + c.getEncriptada());
		inObjeto.close();
		pMod.close();
		cliente.close();
	}
}
