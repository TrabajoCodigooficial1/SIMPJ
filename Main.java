import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== GESTIÓN DE NOTAS ====");
        ArrayList<Object> nombres = new ArrayList<>();
        ArrayList<Object> notas = new ArrayList<>();
        System.out.println("¿Cuántos alumnos deseas ingresar?");
        int n = sc.nextInt();
        int i = 0;
        while (i < n) {
            System.out.println("Introduce el nombre del alumno:");
            String nombre = sc.nextLine();
            System.out.println("Introduce su nota:");
            int nota = sc.nextInt();
            nombres.add(nombre);
            notas.add(nota);
            i = i + 1;
        }
        System.out.println("==== RESULTADOS ====");
        i = 0;
        while (i < n) {
            if (notas.get(i) >= 5) {
                System.out.println(nombres[i]);
                System.out.println(" ha aprobado con nota ");
                System.out.println(notas[i]);
            }
            if (notas.get(i) < 5) {
                System.out.println(nombres[i]);
                System.out.println(" ha suspendido con nota ");
                System.out.println(notas[i]);
            }
            i = i + 1;
        }
        int suma = 0;
        i = 0;
        while (i < n) {
            suma = suma + notas[i];
            i = i + 1;
        }
        Object promedio = suma / n;
        System.out.println("Promedio de la clase:");
        System.out.println(promedio);
    }
}
