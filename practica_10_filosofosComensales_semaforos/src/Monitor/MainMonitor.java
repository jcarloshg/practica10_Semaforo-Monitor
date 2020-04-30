package Monitor;


/**
 * MainMonitor
 */
public class MainMonitor {

    public static void main(String[] args) {
        
        BufferLimitadoMonitor buffer = new BufferLimitadoMonitor();
        Filosofo f0 = new Filosofo(buffer, 0);
        Filosofo f1 = new Filosofo(buffer, 1);
        Filosofo f2 = new Filosofo(buffer, 2);
        Filosofo f3 = new Filosofo(buffer, 3);
        Filosofo f4 = new Filosofo(buffer, 4);
    }
}