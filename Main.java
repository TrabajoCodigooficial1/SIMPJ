import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] lista = new int[]{10, 20, 30, 40, 50};
        int suma = 0;
        int i = 0;
        while (i < 5) {
            suma = suma + lista[i];
            i = i + 1;
        }
        System.out.println(suma);
    }
}
