package AE2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lanzador {
	
	/*
	 * - M�todo: lanzarCalcularProbabilidadColisi�n
	 * - Descripci�n: Este m�todo ejecuta la clase CalcularProbabilidadColisi�n y hace que
	 *   sus args[0] contengan la linea que se le desee pasar. 
	 * - Par�metros de entrada: (String) linea. 
	 * - Par�metros de salida: (Process) process.
	 * 
	 */
	
	public Process lanzarCalculaProbabilidadColisi�n(String linea) {
		String clase = "AE2.CalculaProbabilidadColisi�n";
		ProcessBuilder builder;
		Process process = null;
		try {

			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(linea.toString());

			builder = new ProcessBuilder(command);
			process = builder.inheritIO().start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return process;//devolvemos el proceso para despu�s poder controlarlo
	}
	
	/*
	 * - M�todo: ContenidoFichero
	 * - Descripci�n: Este m�todo lee todas las lineas de un fichero de texto y las introduce
	 *   una a una en un ArrayList en el que la posici�n 0 hace referencia a la linea 1. 
	 * - Par�metros de entrada: (String) fichero. 
	 * - Par�metros de salida: (ArrayList <String>) contenidoFichero "Una lista con todas las 
	 *   lineas del fichero de texto que le pasamos por par�metro."
	 * 
	 */

	public static ArrayList<String> ContenidoFichero(String fichero) {
		ArrayList<String> contenidoFichero = new ArrayList();
		File f = new File(fichero);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			int i = 0;
			while (linea != null) {

				contenidoFichero.add(linea);
				linea = br.readLine();
				i++;
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return contenidoFichero;
	}
	
	/*
	 * - M�todo: main
	 * - Descripci�n: Este m�todo llama a los m�todos de ContenidoFichero y LanzadorCalculaProbabilidadColisi�n.
	 *   Realmente lo que esta haciendo al llamar a estos m�todos es ejecutar la clase CalculaProbabilidadColisi�n
	 *   con las lineas que desea seg�n los nucleos que tenga tu dispositivo. Aparte devuelve el tiempo de ejecuci�n
	 *   medio por cada vez que se ejecuta el lanzador y el tiempo total de ejecuci�n de la aplicaci�n.
	 * - Par�metros de entrada: (String []) args. 
	 * - Par�metros de salida: No tiene par�metros de salida.
	 * 
	 */

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		String fichero = "NEOs.txt";
		Lanzador l = new Lanzador();
		ArrayList<String> f;
		f = ContenidoFichero(fichero);
		int cores = Runtime.getRuntime().availableProcessors();//Calculamos los nucleso que tiene nuestro dispositivo
		Process process = null;
		int i = 0;
		boolean salir = false;
		try {
			do {
				while (i < cores) {
					if (i < f.size()) {
						//Controlamos la ejecuci�n del m�todo asignandola en una variable(Process).
						process = l.lanzarCalculaProbabilidadColisi�n(f.get(i));

					} else {
						salir = true;
					}
					i++;
				}
				process.waitFor();
				cores += cores;
			} while (!salir);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime() - startTime;
		double tiempo = (endTime / 1e9);
		double tiempototal = Math.round(tiempo * 100.0) / 100.0;
		double tiempomedio = Math.round((tiempo / f.size()) * 100.0) / 100.0;
		System.out.println("Tiempo medio de ejecuci�n por cada NEO: " + tiempomedio + " segundos.");
		System.out.println("Tiempo de ejecuci�n total de la aplicaci�n: " + tiempototal + " segundos.");
	}

}
