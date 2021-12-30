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

public class Peticion implements Runnable {
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;
	Scanner sc = new Scanner(System.in);

	public Peticion(Socket socket) {
		this.socket = socket;
	}
	
	

	public void run() {
		try {
			ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
			Contraseña c = new Contraseña("", "");
			outObjeto.writeObject(c);
			
			System.err.println("SERVIDOR >> Envio a cliente la contraseña para que sea rellenada: ");
			ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
			c = (Contraseña) inObjeto.readObject();
			System.err.println("SERVIDOR >> Recibo de cliente la contraseña en texto plano: " + c.getContraseña());
			outObjeto = new ObjectOutputStream(socket.getOutputStream());
			
			String encriptada=c.Encriptar(c.getContraseña(),c.getTipoContraseña());
			c.setEncriptada(encriptada);
			outObjeto.writeObject(c);
			
			System.err.println("SERVIDOR >> Envio a cliente la contraseña encriptada: "+c.getEncriptada());
			outObjeto.close();
			inObjeto.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
