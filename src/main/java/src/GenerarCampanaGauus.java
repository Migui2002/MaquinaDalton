package src;

import java.util.concurrent.Semaphore;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GenerarCampanaGauus implements Runnable {

	private int nBolitas;
	private MaquinaDalton maquinaDalton;
	private Semaphore semCoorBolitas;
	private Thread thread;

	public GenerarCampanaGauus(int nBolitas, MaquinaDalton maquinaDalton, Semaphore semCoorBolitas) {
		this.nBolitas = nBolitas;
		this.maquinaDalton = maquinaDalton;
		this.semCoorBolitas = semCoorBolitas;

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		int bolitasEnMaquina = 0;
		while (nBolitas > bolitasEnMaquina) {
			try {
				semCoorBolitas.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			bolitasEnMaquina = maquinaDalton.getNBolita();
		}

		SwingUtilities.invokeLater(() -> {
			FrequencyChart ventanaCampanaGaus = new FrequencyChart();
			ventanaCampanaGaus.crearCampanaGaus(maquinaDalton.frecuencias());
			ventanaCampanaGaus.setSize(800, 600);
			ventanaCampanaGaus.setLocationRelativeTo(null);
			ventanaCampanaGaus.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			ventanaCampanaGaus.setVisible(true);
		});
		System.out.println("fin campana gauus");
	}

}
