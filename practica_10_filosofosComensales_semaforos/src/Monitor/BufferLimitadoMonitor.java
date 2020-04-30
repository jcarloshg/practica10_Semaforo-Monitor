
package Monitor;

public class BufferLimitadoMonitor {
	String[] estados_filosofos = new String[5];
	int count = 0;

	/**
	 *  sería el número maxímo
	 *  que pueden estar comiendo  
	 * */
	final int filosofos = 2;

	
	public BufferLimitadoMonitor() 
	{ 
		for (int i = 0; i < 5; i++)
			estados_filosofos[i] = "PENSAR";
	}
 
	/**
	 * 	método ingreso
	 */
	public synchronized void toma_tenedores(final int num_filosofo) 
	{
		estados_filosofos[num_filosofo] = "HAMBRE";

		/**
		 * condiciones de sincronización
		 **/
		while (count == filosofos)
			Util.myWait(this);
		/**
		 * variables de condición (linea 32)
		 **/

		probar_bocado(num_filosofo);
		count++;
	}

	/**
	 * 	método ingreso
	 */
	private void probar_bocado(final int num_filosofo) 
	{

		int izquierda = (num_filosofo - 1) % 5;
		final int derecha = (num_filosofo + 1) % 5;

		if (izquierda == -1)
			izquierda = 4;

		if (estados_filosofos[num_filosofo] == "HAMBRE" && estados_filosofos[izquierda] != "COMER"
				&& estados_filosofos[derecha] != "COMER") {
			estados_filosofos[num_filosofo] = "COMER";
			/**
			* variables de condición
			**/
			notify();
		}
	}

	public synchronized void bajar_tenedores(final int num_filosofo) {

		/**
		 * condiciones de sincronización
		 **/
		while (count == 0)
			Util.myWait(this);
		/**
		* variables de condición (linea 69)
		**/

		estados_filosofos[num_filosofo] = "PENSAR";

		int izquierda = (num_filosofo - 1) % 5;
		final int derecha = (num_filosofo + 1) % 5;

		if (izquierda == -1)
			izquierda = 4;

		probar_bocado(izquierda);
		probar_bocado(derecha); 

		count--;
	}

}
