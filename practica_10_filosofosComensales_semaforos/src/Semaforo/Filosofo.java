package Semaforo;

/**
 * Filosofo
 */
public class Filosofo implements Runnable {
    
    BufferLimitado buffer = null;
    int numFilosofo;

    public Filosofo(BufferLimitado iniB, int numFilosofo) {
        this.buffer = iniB;
        this.numFilosofo = numFilosofo;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            // estado : PENSR, HAMBRE, COMER 
            System.out.println("Filosofo " + numFilosofo + " pensando");
            // metodos :
            buffer.toma_tenedores( numFilosofo );
            System.out.println("Filosofo " + numFilosofo + " comniendo con tenerdor " + numFilosofo + " y tenerdor " + (numFilosofo+1)%5);
            buffer.bajar_tenedores( numFilosofo );
        }
    }
}