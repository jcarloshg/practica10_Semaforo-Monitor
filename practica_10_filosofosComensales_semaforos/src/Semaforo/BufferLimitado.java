package Semaforo;

public class BufferLimitado {
	
	//datos filosofos
	SemaforoBinario mutex = new SemaforoBinario(true);
	// SemaforoContador sinc = new SemaforoContador(5);
	SemaforoBinario sinc[] = new SemaforoBinario[5];
	String estados_filosofos[] = new String[5];

	//filosofos
	int filosofos;

	public BufferLimitado(int num_filosofo) {
		super();

		this.filosofos = num_filosofo-1;

		for (int i = 0; i < num_filosofo; i++)
		{
			estados_filosofos[i] = "PENSAR";
			sinc[i] = new SemaforoBinario(false);
		}
	}
	

	public void toma_tenedores(int num_filosofo) {
		mutex.P(); 
		estados_filosofos[num_filosofo] = "HAMBRE";
		probar_bocado(num_filosofo);
		mutex.V();
		sinc[num_filosofo].P();
	}

	public void probar_bocado(int num_filosofo) {

		int atras = num_filosofo-1 , 
			centro = num_filosofo,
			delante = num_filosofo+1;
		if(num_filosofo == 0)
		{
			atras = filosofos; 
			centro = 0;
			delante = 1;
		}
		if (num_filosofo == filosofos) {
			atras = filosofos-1; 
			centro = filosofos;
			delante = 0;
		}
		// System.out.println("come:" + atras +" "+ centro +" "+ delante  );
		if (estados_filosofos[centro]	== "HAMBRE" && 
			estados_filosofos[atras]	!= "COMER" && 
			estados_filosofos[delante]	!= "COMER"	) 
				{
					estados_filosofos[num_filosofo] = "COMER";
					sinc[num_filosofo].V();
		}
	}

	public void bajar_tenedores(int num_filosofo) {
		mutex.P();

		int atras = num_filosofo-1 , 
			delante = num_filosofo+1;

		if(num_filosofo == 0)
		{
			atras = filosofos; 
			delante = 1;
		}
		if (num_filosofo == filosofos) {
			atras = filosofos-1; 
			delante = 0;
		}
		
		// System.out.println("deja:" + atras +" "+ delante  );
		estados_filosofos[num_filosofo] = "PENSAR";
		probar_bocado(atras);
		probar_bocado(delante);
		mutex.V();
	}
	
}
