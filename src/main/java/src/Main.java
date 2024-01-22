package src;

import java.util.concurrent.Semaphore;

public class Main {

	private static final int NUM_BOLITAS = 100;
	private static final int TAM_BUFFER = 25;
	private static final int CLAVOS = 6;

	public static void main(String[] args) throws InterruptedException {

		// REcursos compartidos
		Semaphore semCoorBolitas = new Semaphore(1);
		MaquinaDalton maquinaDalton = new MaquinaDalton(CLAVOS, semCoorBolitas);
		Buffer buffer = new Buffer(TAM_BUFFER);
		
		// Hilos
		new Consumidor(buffer, NUM_BOLITAS);
		new Productor(buffer, NUM_BOLITAS, maquinaDalton);
		new GenerarCampanaGauus(NUM_BOLITAS, maquinaDalton, semCoorBolitas);

//		Bolita[] arrayBolita = new Bolita[NUM_BOLITAS];
//		for (int i = 1; i <= NUM_BOLITAS; i++) {
//			arrayBolita[i - 1] = new Bolita(i, maquinaDalton);
//		}
//		for (int i = 0; i < NUM_BOLITAS; i++) {
//			arrayBolita[i].join();
//		}
//		System.out.println(maquinaDalton);
//		double[] arrayFrecuencias = maquinaDalton.frecuencias();

//		SwingUtilities.invokeLater(() -> {
//			FrequencyChartExample ventanaCampanaGaus = new FrequencyChartExample();
//			ventanaCampanaGaus.crearCampanaGaus(arrayFrecuencias);
//			ventanaCampanaGaus.setSize(800, 600);
//			ventanaCampanaGaus.setLocationRelativeTo(null);
//			ventanaCampanaGaus.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//			ventanaCampanaGaus.setVisible(true);
//		});
		System.out.println("fin main");
	}

}
