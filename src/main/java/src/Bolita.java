package src;

/**
 * Hilo que representa una bola que cae por la máquina de Dalton.
 * 
 * @author Miguel
 *
 */
public class Bolita implements Runnable {

	private int id;
	private Thread thread;
	private MaquinaDalton maquinaGalton;

	public Bolita(int id, MaquinaDalton maquinaGalton) {
		this.id = id;
		this.maquinaGalton = maquinaGalton;
		thread = new Thread(this);
	}

	public void start() {
		thread.start();
	}

	public int getId() {
		return id;
	}

	@Override
	public void run() {
		System.out.println("Se lanza la bolita " + id);
		int clavos = maquinaGalton.getClavos();

		int j = maquinaGalton.getColumnas() / 2;
		while (clavos > 0) {
			int impacto = bolitaImpactaClavo();
			if (impacto == 0) {
				j--;
			} else {
				j++;
			}
			clavos--;
		}

//		System.out.println("Actualiza contador j " + j);
		maquinaGalton.actualizaContador(j);
	}

	public int bolitaImpactaClavo() {
		return (int) Math.floor(Math.random() * 2);
	}

	public void join() throws InterruptedException {
		thread.join();

	}
}
