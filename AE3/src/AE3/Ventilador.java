package AE3;

public class Ventilador {
	boolean estado=true;
	int tiempo = 2000;

	/*
	 * - M�todo: encenderVentilador
	 * - Descripci�n: Este m�todo vuelve el estado a true encendiendo el ventilador. 
	 * - Par�metros de entrada: Ning�n par�meter d'entrada. 
	 * - Par�metros de salida: Ning�n par�meter d'eixida.
	 * 
	 */
	
	public void encenderVentilador() {
		while (true) {
			synchronized (this) {
				try {
					while (!estado) wait();
					
					System.out.println("Ventilador Encendido");
					Thread.sleep(tiempo);
					estado=false;
					notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * - M�todo: apagarVentilador
	 * - Descripci�n: Este m�todo vuelve el estado a false apagando el ventilador. 
	 * - Par�metros de entrada: Ning�n par�metro d'entrada. 
	 * - Par�metros de salida: Ning�n par�metro d'eixida.
	 * 
	 */
	
	public void apagarVentilador() {
		while (true) {
			synchronized (this) {
				try {
					while (estado) wait();
					System.err.println("Ventilador Apagado");
					Thread.sleep(tiempo);
					estado=true;
					notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
