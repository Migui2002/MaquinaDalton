package src;

import java.util.concurrent.Semaphore;

/**
 * Maquina de Dalton. Se considera un recurso compartido por todas las bolitas.
 * 
 * @author Miguel
 *
 */
public class MaquinaDalton {

	private int nBolita;
	private final int clavos;
	private final int[][] tablero;
	private final Semaphore[] semContador;
	private final Semaphore semBolita;
	private final Semaphore semCoorBolitas;

	public MaquinaDalton(int clavos, Semaphore semCoorBolitas) {
		nBolita = 0;
		this.semCoorBolitas = semCoorBolitas;
		semBolita = new Semaphore(1);

		this.clavos = clavos;
		tablero = new int[clavos + 1][2 * clavos - 1 + 2];
		semContador = new Semaphore[2 * clavos - 1 + 2];

		for (int i = 0; i < semContador.length; i++) {
			semContador[i] = new Semaphore(1);
		}

		// Inicialmente el tablero está vacio
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = -2;
			}
		}
		for (int j = 0; j < tablero[0].length; j++) {
			if (j % 2 == 0) {
				tablero[tablero.length - 1][j] = 0;
			} else {
				tablero[tablero.length - 1][j] = -2;
			}
		}

		// Incluimos los clavos en el tablero
		for (int i = tablero.length - 2, k = 1; i >= 0; i--, k++) {
			for (int j = k, numClavos = 0; j < tablero[i].length; j += 2) {
				if (numClavos <= i) {
					numClavos++;
					tablero[i][j] = -1;
				}
			}
		}
	}

	public synchronized int getNBolita() {
		return nBolita;
	}

	public int getFilas() {
		return tablero.length;
	}

	public int getColumnas() {
		return tablero[0].length;
	}

	public int getClavos() {
		return clavos;
	}

	public void actualizaContador(int j) {
		try {
			semContador[j].acquire();
			tablero[tablero.length - 1][j]++;
			semContador[j].release();

			semBolita.acquire();
			nBolita++;
			semBolita.release();
			semCoorBolitas.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public double[] frecuencias() {
		double[] arrayFrecuencia = new double[clavos + 1];
		for (int i = 0, k = 0; i < arrayFrecuencia.length; i++, k += 2) {
			arrayFrecuencia[i] = tablero[tablero.length - 1][k];
		}

		return arrayFrecuencia;
	}

	@Override
	public String toString() {
		String str = "MaquinaGalton [tablero=\n\n";
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				int elem = tablero[i][j];
				if (elem == -1) {
					str += "c   ";
				}
				if (elem == -2) {
					str += "    ";
				} else if (elem >= 0) {
					str += " " + tablero[i][j] + " ";
				}
			}
			str += "\n";
		}
		str += "\n]";

		return str;
	}

}
