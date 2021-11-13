package AE3;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {

		Mina mina = new Mina(505);

		int recursosTotales = 0;

		Thread hiloMinero;
		ArrayList<Minero> mineros = new ArrayList();
		for (int i = 0; i < 10; i++) {
			Minero minero = new Minero(mina);
			mineros.add(minero);
			hiloMinero = new Thread(minero);
			hiloMinero.setName("Minero " + (i + 1));
			hiloMinero.start();

		}

		Ventilador v = new Ventilador();
		Thread encendido = new Thread(new Runnable() {
			@Override
			public void run() {
				v.encenderVentilador();
			}
		});

		Thread apagado = new Thread(new Runnable() {
			@Override
			public void run() {
				v.apagarVentilador();
			}
		});

		encendido.start();
		apagado.start();

		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

		for (int h = 0; h < mineros.size(); h++) {
			recursosTotales += mineros.get(h).bolsa;
		}

		System.out.println("Stock actual de la mina: " + mina.stock);
		System.out.println("Total extraido: " + recursosTotales);

	}

}
