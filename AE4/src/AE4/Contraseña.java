package AE4;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
public class Contraseña implements Serializable {

	String contraseña;
	String encriptada;
	int tipoContraseña;

	/*
	 * - Método: Contraseña 
	 * - Descripción: Este método hace de constructor para la clase Contraseña. 
	 * - Parámetros de entrada: String c, e. 
	 * - Parámetros de salida: Ningún parámetro de salida.
	 * 
	 */

	public Contraseña(String c, String e) {
		this.contraseña = c;
		this.encriptada = e;
	}

	public Contraseña() {
		super();
	}

	/*
	 * - Método: get 
	 * - Descripción: Este método retorna las variables de la clase contraseña. 
	 * - Parámetros de entrada: Ningún parámetro de entrada. 
	 * - Parámetros de salida: String contraseña, encriptada y int tipoContraseña.
	 * 
	 */

	public String getContraseña() {
		return contraseña;
	}

	public String getEncriptada() {
		return encriptada;
	}

	public int getTipoContraseña() {
		return tipoContraseña;
	}

	/*
	 * - Método: set 
	 * - Descripción: Este método añade un nuevo valor a las variables de la clase contraseña . 
	 * - Parámetros de entrada: String contraseña, encriptada y int tipoContraseña. 
	 * - Parámetros de salida: Ningún parámetro de salida.
	 * 
	 */

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void setEncriptada(String encriptada) {
		this.encriptada = encriptada;
	}

	public void setTipoContraseña(int tipoContraseña) {
		this.tipoContraseña = tipoContraseña;
	}

	/*
	 * - Método: Encriptar 
	 * - Descripción: Este método encripta un String según el tipo de encriptación 
	 *   que se le haya pasado por parámetro (Básica o MD5) y lo retorna. 
	 * - Parámetros de entrada: String contraseña y int tipo. 
	 * - Parámetros de salida: String encriptada.
	 * 
	 */

	public String Encriptar(String contraseña, int tipo) {
		String encriptada = "";

		if (tipo == 1) {
			for (int i = 0; i < contraseña.length(); i++) {

				char c = contraseña.charAt(i);

				if (c <= 31 || c == 127) {
					c = '*';
				}else {
					c++;
				}

				encriptada += c;

			}
		} else {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(contraseña.getBytes());
				BigInteger number = new BigInteger(1, messageDigest);
				encriptada = number.toString(16);

				while (encriptada.length() < 32) {
					encriptada = "0" + encriptada;
				}

			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}

		return encriptada;
	}

}
