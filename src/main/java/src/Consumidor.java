package src;

public class Consumidor implements Runnable {

	private Buffer buffer;
	private Thread thread;
	private int nBolitas;

	public Consumidor(Buffer buffer, int nBolitas) {
		this.buffer = buffer;
		this.nBolitas = nBolitas;
		this.thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		int i = 0;
		while (i < nBolitas) {
			try {
				Bolita bolita = buffer.extraer();
				// System.out.println("bolita " + bolita.getId());
				i++;
				bolita.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("fin consumidor");
	}

}
