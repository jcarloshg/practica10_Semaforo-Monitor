package Semaforo;

import java.util.Scanner;
/**
 * MainSemaforo
 */
public class MainSemaforo {

    public static void main(String[] args) {
        int num_filosofos;
        Scanner leer = new Scanner(System.in);
        System.out.println("\nNúmero de filosofos: ");
        num_filosofos = leer.nextInt();

        BufferLimitado buffer = new BufferLimitado(num_filosofos);
        Filosofo filosofos[] = new Filosofo[num_filosofos];
        for (int i = 0; i < filosofos.length; i++) {
            filosofos[i] = new Filosofo(buffer, i);
        }
    }
}