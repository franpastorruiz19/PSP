package AE3;

public class Ventilador {
	boolean estado=true;
	int tiempo = 2000;

	/*
	 * - Método: encenderVentilador
	 * - Descripción: Este método vuelve el estado a true encendiendo el ventilador. 
	 * - Parámetros de entrada: Ningún parámeter d'entrada. 
	 * - Parámetros de salida: Ningún parámeter d'eixida.
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
	 * - Método: apagarVentilador
	 * - Descripción: Este método vuelve el estado a false apagando el ventilador. 
	 * - Parámetros de entrada: Ningún parámetro d'entrada. 
	 * - Parámetros de salida: Ningún parámetro d'eixida.
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
