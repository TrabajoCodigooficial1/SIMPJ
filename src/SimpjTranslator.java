import java.io.*;
import java.util.*;

public class SimpjTranslator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java SimpjTranslator archivo.simpj");
            return;
        }

        String inputFile = args[0];
        String outputFile = "Main.java";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {

            pw.println("import java.util.*;");
            pw.println("public class Main {");
            pw.println("    public static void main(String[] args) {");
            pw.println("        Scanner sc = new Scanner(System.in);");

            Map<String, String> variables = new HashMap<>();
            int indent = 8;

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String indentSpace = " ".repeat(indent);

                // Imprimir
                if (line.startsWith("imprimir ")) {
                    String contenido = line.substring(9).trim();
                    pw.println(indentSpace + "System.out.println(" + contenido + ");");
                }
                // Leer entrada
                else if (line.matches("\\w+\\s*=\\s*leer\\(\\)")) {
                    String var = line.split("=")[0].trim();
                    pw.println(indentSpace + "int " + var + " = sc.nextInt();");
                    variables.put(var, "int");
                }
                // Asignaciones
                else if (line.matches("\\w+\\s*=.*")) {
                    String[] parts = line.split("=", 2);
                    String var = parts[0].trim();
                    String val = parts[1].trim();

                    if (!variables.containsKey(var)) {
                        if (val.startsWith("\"") && val.endsWith("\"")) {
                            pw.println(indentSpace + "String " + var + " = " + val + ";");
                            variables.put(var, "String");
                        } else {
                            // Se asume expresión numérica
                            pw.println(indentSpace + "int " + var + " = " + val + ";");
                            variables.put(var, "int");
                        }
                    } else {
                        pw.println(indentSpace + var + " = " + val + ";");
                    }
                }
                // Condicional
                else if (line.startsWith("si ") && line.endsWith(":")) {
                    String condition = line.substring(3, line.length() - 1).trim();
                    pw.println(indentSpace + "if (" + condition + ") {");
                    indent += 4;
                } else if (line.equals("fin_si")) {
                    indent -= 4;
                    pw.println(" ".repeat(indent) + "}");
                }
                // Bucle
                else if (line.startsWith("mientras ") && line.endsWith(":")) {
                    String condition = line.substring(8, line.length() - 1).trim();
                    pw.println(indentSpace + "while (" + condition + ") {");
                    indent += 4;
                } else if (line.equals("fin_mientras")) {
                    indent -= 4;
                    pw.println(" ".repeat(indent) + "}");
                }
                // Línea no reconocida
                else {
                    System.out.println("Línea no reconocida: " + line);
                }
            }

            pw.println("    }");
            pw.println("}");
            System.out.println("Traducción completada. Archivo generado: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
