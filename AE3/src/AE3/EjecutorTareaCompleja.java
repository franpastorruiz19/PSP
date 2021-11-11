package AE3;

	class EjecutorTareaCompleja implements Runnable {
		private String nombre;
		int numEjecucion=100;

		public EjecutorTareaCompleja(String nombre) {
			this.nombre = nombre;
		}

		public void run() {
			String cad;
			while (numEjecucion !=0) {
				for (double i = 0; i < 10; i = i + 0.02) {
					Math.sqrt(i);
				}
				cad = "Soy el minero " + this.nombre;
				cad += ", i= " + numEjecucion;
				System.out.println(cad);
				numEjecucion--;
			}
		}
		
		public static void main(String[] args) {
			
		}
	}


