package AE4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

	public static void main(String[] arg) throws IOException, ClassNotFoundException {
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			return;
		}
		while (true) {
			Socket conexion = servidor.accept();
			System.err.println("SERVIDOR >>> Conexion recibida --> Lanza hilo clasePeticion");
			Peticion p = new Peticion(conexion);
			Thread hilo = new Thread(p);
			hilo.start();
		}

	}

}
