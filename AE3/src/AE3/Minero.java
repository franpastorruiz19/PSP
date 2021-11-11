package AE3;

public class Minero implements Runnable {
	int bolsa;
	int tiempoExtraccion;
	Mina mina;

	
	/*
	 * - M�todo: Minero
	 * - Descripci�n: Este m�todo hace de constructor para la clase Minero . 
	 * - Par�metros de entrada: Mina m. 
	 * - Par�metros de salida: Ning�n par�metro de salida.
	 * 
	 */
	
	public Minero(Mina m) {
		bolsa = 0;
		tiempoExtraccion = 150;
		mina = m;
	}

	/*
	 * - M�todo: extraerRecurso
	 * - Descripci�n: Este m�todo extrae de uno en uno recursos de la mina
	 *   hasta que el stock de la mina se queda a cero . 
	 * - Par�metros de entrada: Ning�n par�metro de entrada. 
	 * - Par�metros de salida: Ning�n par�metro de salida.
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
					// TODO Bloque catch generado autom�ticamente
					e.printStackTrace();
				}
			}

		}

	
	@Override
	public void run() {
		extraerRecurso();
	}
}
