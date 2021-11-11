package AE3;

import AE3.EjecutorTareaCompleja;

public class LanzarHilo {

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		int NUM_HILOS = 10;
		EjecutorTareaCompleja op;
		for (int i = 0; i < NUM_HILOS; i++) {
			op = new EjecutorTareaCompleja(""+i);
			Thread hilo = new Thread(op);
			hilo.start();
		}
	}

}
