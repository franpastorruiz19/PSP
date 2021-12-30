package AE4;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
public class Contrase�a implements Serializable {

	String contrase�a;
	String encriptada;
	int tipoContrase�a;

	/*
	 * - M�todo: Contrase�a 
	 * - Descripci�n: Este m�todo hace de constructor para la clase Contrase�a. 
	 * - Par�metros de entrada: String c, e. 
	 * - Par�metros de salida: Ning�n par�metro de salida.
	 * 
	 */

	public Contrase�a(String c, String e) {
		this.contrase�a = c;
		this.encriptada = e;
	}

	public Contrase�a() {
		super();
	}

	/*
	 * - M�todo: get 
	 * - Descripci�n: Este m�todo retorna las variables de la clase contrase�a. 
	 * - Par�metros de entrada: Ning�n par�metro de entrada. 
	 * - Par�metros de salida: String contrase�a, encriptada y int tipoContrase�a.
	 * 
	 */

	public String getContrase�a() {
		return contrase�a;
	}

	public String getEncriptada() {
		return encriptada;
	}

	public int getTipoContrase�a() {
		return tipoContrase�a;
	}

	/*
	 * - M�todo: set 
	 * - Descripci�n: Este m�todo a�ade un nuevo valor a las variables de la clase contrase�a . 
	 * - Par�metros de entrada: String contrase�a, encriptada y int tipoContrase�a. 
	 * - Par�metros de salida: Ning�n par�metro de salida.
	 * 
	 */

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public void setEncriptada(String encriptada) {
		this.encriptada = encriptada;
	}

	public void setTipoContrase�a(int tipoContrase�a) {
		this.tipoContrase�a = tipoContrase�a;
	}

	/*
	 * - M�todo: Encriptar 
	 * - Descripci�n: Este m�todo encripta un String seg�n el tipo de encriptaci�n 
	 *   que se le haya pasado por par�metro (B�sica o MD5) y lo retorna. 
	 * - Par�metros de entrada: String contrase�a y int tipo. 
	 * - Par�metros de salida: String encriptada.
	 * 
	 */

	public String Encriptar(String contrase�a, int tipo) {
		String encriptada = "";

		if (tipo == 1) {
			for (int i = 0; i < contrase�a.length(); i++) {

				char c = contrase�a.charAt(i);

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
				byte[] messageDigest = md.digest(contrase�a.getBytes());
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
