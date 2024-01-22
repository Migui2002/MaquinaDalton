package src;

public class Productor implements Runnable {

	private Thread thread;

	private Buffer buffer;
	private int nBolitas;
	private MaquinaDalton maquinaDalton;

	public Productor(Buffer buffer, int nBolitas, MaquinaDalton maquinaDalton) {
		this.buffer = buffer;
		this.nBolitas = nBolitas;
		this.maquinaDalton = maquinaDalton;
		this.thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		int i = 0;
		while (i < nBolitas) {
			Bolita bolita = new Bolita(i + 1, maquinaDalton);
			try {
				buffer.almacenar(bolita);
				i++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("fin productor");

	}

}
