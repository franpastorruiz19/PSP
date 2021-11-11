package AE3;

public class Minero implements Runnable {
	int bolsa;
	int tiempoExtraccion;
	Mina mina;

	
	/*
	 * - Método: Minero
	 * - Descripción: Este método hace de constructor para la clase Minero . 
	 * - Parámetros de entrada: Mina m. 
	 * - Parámetros de salida: Ningún parámetro de salida.
	 * 
	 */
	
	public Minero(Mina m) {
		bolsa = 0;
		tiempoExtraccion = 150;
		mina = m;
	}

	/*
	 * - Método: extraerRecurso
	 * - Descripción: Este método extrae de uno en uno recursos de la mina
	 *   hasta que el stock de la mina se queda a cero . 
	 * - Parámetros de entrada: Ningún parámetro de entrada. 
	 * - Parámetros de salida: Ningún parámetro de salida.
	 * 
	 */
	
	public void extraerRecurso() {
		
			while (mina.stock > 0) {
				synchronized(mina) {
					bolsa++;
					mina.stock--;
					System.out.println(Thread.currentThread().getName() + " tiene en su bolsa " + bolsa + " y el stock es de " + mina.stock);
				}
				

				try {
					Thread.sleep(tiempoExtraccion);
				} catch (InterruptedException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}

		}

	
	@Override
	public void run() {
		extraerRecurso();
	}
}
