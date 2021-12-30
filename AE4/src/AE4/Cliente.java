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
		Contrase�a c = (Contrase�a) inObjeto.readObject();
		System.out.println("CLIENTE>> Recibo de servidor la contrase�a para que la rellene ");
		System.out.print("Escribe la contrase�a: ");
		c.setContrase�a(sc.nextLine());
		boolean salir = false;
		int tipo = 0;
		System.out.println("1- Encriptaci�n Basica");
		System.out.println("2- Encriptaci�n MD5");
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
		c.setTipoContrase�a(tipo);
		ObjectOutputStream pMod = new ObjectOutputStream(cliente.getOutputStream());
		pMod.writeObject(c);
		System.out.println("CLIENTE >> Envio al servidor: " + c.contrase�a + " para que sea encriptada");
		inObjeto = new ObjectInputStream(cliente.getInputStream());
		c = (Contrase�a) inObjeto.readObject();
		System.out.println("CLIENTE>> Recibo de servidor la contrase�a encriptada: " + c.getEncriptada());
		System.out.println("Contrase�a: " + c.getContrase�a());
		if(c.getTipoContrase�a()==1) {
			System.out.println("Tipo de encriptaci�n: B�sica ");
		}else {
			System.out.println("Tipo de encriptaci�n: MD5 ");
		}
		System.out.println("Contrase�a encriptada: " + c.getEncriptada());
		inObjeto.close();
		pMod.close();
		cliente.close();
	}
}
