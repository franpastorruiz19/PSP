package AE2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class CalculaProbabilidadColisión {

	/*
	 * - Método: ProbColisión
	 * - Descripción: Este método calcula la probabilidad de que un NEO colisione
	 *   con la tierra a partir de la posición y la velocidad del mismo. 
	 * - Parámetros de entrada: (String) posicionNEO y (String) velocidadNEO. 
	 * - Parámetros de salida: (double) resultado "Calculo de la probabilidad de colisión"
	 * 
	 */
	public double ProbColisión(double posicionNEO, double velocidadNEO) {
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
	 * - Método: CreaCarpeta
	 * - Descripción: Este método crea un fichero de texto si no existe, con el nombre del NEO del que 
	 *   se ha calculado la probabilidad de colisión y se guarda la probabilidad en el fichero. 
	 * - Parámetros de entrada: (double) resultado y (String) nombre. 
	 * - Parámetros de salida: No hay parámetros de salida.
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

			writer.write(result); //Escribimos en el fichero el resultado del calculo de la probabilidad de colisión.

			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * - Método: main
	 * - Descripción: Este método contiene el programa y ejecuta los métodos ProbColisión y CreaCarpeta,
	 *   se ecarga también de mostrar los pertinentes mensajes dependiendo del resultado que haya
	 *   devuelto el método ProbColisión.
	 * - Parámetros de entrada: (String []) args. 
	 * - Parámetros de salida: No tiene parámetros de salida
	 * 
	 */

	public static void main(String[] args) {
		CalculaProbabilidadColisión cpc = new CalculaProbabilidadColisión();
		String linea = args[0];
		String[] partes = linea.split(",");
		String nombre = partes[0];
		double posicionNEO = Double.parseDouble(partes[1]);
		double velocidadNEO = Double.parseDouble(partes[2]);
		double probabilidad = cpc.ProbColisión(posicionNEO, velocidadNEO);
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
