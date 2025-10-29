import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== GESTIÓN DE NOTAS ===");
        ArrayList<Object> nombres = new ArrayList<>();
        ArrayList<Object> notas = new ArrayList<>();
        System.out.println("¿Cuántos alumnos?");
        int n = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        int i = 0;
        while (i < n) {
            System.out.println("Nombre del alumno:");
            String nombre = sc.nextLine();
            System.out.println("Nota:");
            int nota = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            nombres.add(nombre);
            notas.add(nota);
            i = i + 1;
        }
        i = 0;
        while (i < n) {
            if ((int)notas.get(i) >= 5) {
                System.out.println((nombres.get(i))  +  " ha aprobado con nota "  +  (notas.get(i)));
            }
            if ((int)notas.get(i) < 5) {
                System.out.println((nombres.get(i))  +  " ha suspendido con nota "  +  (notas.get(i)));
            }
            i = i + 1;
        }
        int suma = 0;
        i = 0;
        while (i < n) {
            suma = suma + ((int)notas.get(i));
            i = i + 1;
        }
        Object promedio = suma / n;
        System.out.println("Promedio de la clase: "  +  promedio);
    }
}
