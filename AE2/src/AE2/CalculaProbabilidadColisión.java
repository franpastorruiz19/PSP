package AE2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class CalculaProbabilidadColisi�n {

	/*
	 * - M�todo: ProbColisi�n
	 * - Descripci�n: Este m�todo calcula la probabilidad de que un NEO colisione
	 *   con la tierra a partir de la posici�n y la velocidad del mismo. 
	 * - Par�metros de entrada: (String) posicionNEO y (String) velocidadNEO. 
	 * - Par�metros de salida: (double) resultado "Calculo de la probabilidad de colisi�n"
	 * 
	 */
	public double ProbColisi�n(double posicionNEO, double velocidadNEO) {
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
			posicionNEO = posicionNEO + velocidadNEO * i;
			posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random()
				* Math.pow(((posicionNEO - posicionTierra) / (posicionNEO + posicionTierra)), 2);
		resultado = (Math.round(resultado * 100d) / 100d);
		return resultado;
	}
	
	/*
	 * - M�todo: CreaCarpeta
	 * - Descripci�n: Este m�todo crea un fichero de texto si no existe, con el nombre del NEO del que 
	 *   se ha calculado la probabilidad de colisi�n y se guarda la probabilidad en el fichero. 
	 * - Par�metros de entrada: (double) resultado y (String) nombre. 
	 * - Par�metros de salida: No hay par�metros de salida.
	 * 
	 */

	public void CreaCarpeta(double resultado, String nombre) {
		try {
			File directorio = new File("./" + nombre + ".txt");
			String result = String.valueOf(resultado);
			if (!directorio.exists()) {
				directorio.createNewFile();
			}

			FileWriter writer = new FileWriter(directorio);

			writer.write(result); //Escribimos en el fichero el resultado del calculo de la probabilidad de colisi�n.

			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * - M�todo: main
	 * - Descripci�n: Este m�todo contiene el programa y ejecuta los m�todos ProbColisi�n y CreaCarpeta,
	 *   se ecarga tambi�n de mostrar los pertinentes mensajes dependiendo del resultado que haya
	 *   devuelto el m�todo ProbColisi�n.
	 * - Par�metros de entrada: (String []) args. 
	 * - Par�metros de salida: No tiene par�metros de salida
	 * 
	 */

	public static void main(String[] args) {
		CalculaProbabilidadColisi�n cpc = new CalculaProbabilidadColisi�n();
		String linea = args[0];
		String[] partes = linea.split(",");
		String nombre = partes[0];
		double posicionNEO = Double.parseDouble(partes[1]);
		double velocidadNEO = Double.parseDouble(partes[2]);
		double probabilidad = cpc.ProbColisi�n(posicionNEO, velocidadNEO);
		cpc.CreaCarpeta(probabilidad, nombre);

		if (probabilidad > 10) {
			System.out.println(
					"!Alerta mundial! " + nombre + " tiene probabilidad de colisionar con La Tierra.\nProbabilidad de "
							+ nombre + " de colisionar con La Tierra: " + probabilidad + "%\n");
		} else {
			System.out.println("Esto es un mensaje de tranquilidad para la humanidad " + nombre
					+ " no tiene probabilidad de colisionar con La Tierra \nProbabilidad de " + nombre
					+ " de colisionar con La Tierra: " + probabilidad + "%\n");
		}
		

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
