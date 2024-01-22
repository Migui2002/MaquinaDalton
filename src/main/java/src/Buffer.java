package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Recurso compartido por los productores y consumidores
 * 
 * @author Miguel
 *
 */
public class Buffer {

	private List<Bolita> lstBolita;
	private int capacidad;

	public Buffer(int capacidad) {
		this.capacidad = capacidad;
		this.lstBolita = new ArrayList<>(capacidad);
	}

	synchronized public void almacenar(Bolita bolita) throws InterruptedException {

		while (lstBolita.size() == capacidad) {
			System.out.println("Productor duerme porque la lista esta llena");
			wait();
		}
		lstBolita.add(0, bolita);
		notifyAll();
	}

	synchronized public Bolita extraer() throws InterruptedException {

		while (lstBolita.size() == 0) {
			System.out.println("Consumidor duerme porque la lista esta vacia");
			wait();
		}

		Bolita bolita = lstBolita.remove(lstBolita.size() - 1);
		notifyAll();
		return bolita;
	}
}
