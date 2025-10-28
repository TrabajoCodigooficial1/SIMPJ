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
            boolean inMultilineComment = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Comentarios multilínea
                if (line.startsWith("'''")) {
                    inMultilineComment = !inMultilineComment;
                    continue;
                }
                if (inMultilineComment || line.startsWith("#")) continue;

                String indentSpace = " ".repeat(indent);

                // Imprimir
                if (line.startsWith("imprimir ")) {
                    String contenido = line.substring(9).trim();
                    pw.println(indentSpace + "System.out.println(" + contenido + ");");
                }

                // Leer entero
                else if (line.matches("\\w+\\s*=\\s*leer\\(\\)")) {
                    String var = line.split("=")[0].trim();
                    pw.println(indentSpace + "int " + var + " = sc.nextInt();");
                    variables.put(var, "int");
                }

                // Leer string
                else if (line.matches("\\w+\\s*=\\s*leer_string\\(\\)")) {
                    String var = line.split("=")[0].trim();
                    pw.println(indentSpace + "String " + var + " = sc.nextLine();");
                    variables.put(var, "String");
                }

                // Declarar lista vacía
                else if (line.matches("\\w+\\s*=\\s*\\[\\s*\\]")) {
                    String var = line.split("=")[0].trim();
                    pw.println(indentSpace + "ArrayList<Object> " + var + " = new ArrayList<>();");
                    variables.put(var, "ArrayList");
                }

                // Concatenar listas (ej: lista = lista + [valor])
                else if (line.matches("\\w+\\s*=\\s*\\w+\\s*\\+\\s*\\[.*\\]")) {
                    String var = line.split("=")[0].trim();
                    String base = line.split("=")[1].split("\\+")[0].trim();
                    String valor = line.substring(line.indexOf("[") + 1, line.lastIndexOf("]")).trim();
                    pw.println(indentSpace + base + ".add(" + valor + ");");
                }

                // Listas con contenido inicial (ej: lista = [1,2,3])
                else if (line.matches("\\w+\\s*=\\s*\\[.*\\]")) {
                    String var = line.split("=")[0].trim();
                    String contenido = line.substring(line.indexOf("[") + 1, line.lastIndexOf("]")).trim();
                    pw.println(indentSpace + "ArrayList<Object> " + var + " = new ArrayList<>(Arrays.asList(" + contenido + "));");
                    variables.put(var, "ArrayList");
                }

                // Asignaciones simples
                else if (line.matches("\\w+\\s*=.*")) {
                    String[] parts = line.split("=", 2);
                    String var = parts[0].trim();
                    String val = parts[1].trim();

                    if (!variables.containsKey(var)) {
                        if (val.startsWith("\"") && val.endsWith("\"")) {
                            pw.println(indentSpace + "String " + var + " = " + val + ";");
                            variables.put(var, "String");
                        } else if (val.equals("true") || val.equals("false")) {
                            pw.println(indentSpace + "boolean " + var + " = " + val + ";");
                            variables.put(var, "boolean");
                        } else if (val.matches("-?\\d+")) {
                            pw.println(indentSpace + "int " + var + " = " + val + ";");
                            variables.put(var, "int");
                        } else if (val.matches("-?\\d+\\.\\d+")) {
                            pw.println(indentSpace + "double " + var + " = " + val + ";");
                            variables.put(var, "double");
                        } else {
                            pw.println(indentSpace + "Object " + var + " = " + val + ";");
                            variables.put(var, "Object");
                        }
                    } else {
                        pw.println(indentSpace + var + " = " + val + ";");
                    }
                }

                // Condicionales
                else if (line.startsWith("si ") && line.endsWith(":")) {
                    String condition = line.substring(3, line.length() - 1).trim();
                    condition = condition.replaceAll("(\\w+)\\[(\\w+)\\]", "$1.get($2)");
                    pw.println(indentSpace + "if (" + condition + ") {");
                    indent += 4;
                } else if (line.equals("fin_si")) {
                    indent -= 4;
                    pw.println(" ".repeat(indent) + "}");
                }

                // Bucles
                else if (line.startsWith("mientras ") && line.endsWith(":")) {
                    String condition = line.substring(8, line.length() - 1).trim();
                    condition = condition.replaceAll("(\\w+)\\[(\\w+)\\]", "$1.get($2)");
                    pw.println(indentSpace + "while (" + condition + ") {");
                    indent += 4;
                } else if (line.equals("fin_mientras")) {
                    indent -= 4;
                    pw.println(" ".repeat(indent) + "}");
                }

                // Reemplazo para accesos tipo lista[i]
                else if (line.contains("[")) {
                    line = line.replaceAll("(\\w+)\\[(\\w+)\\]", "$1.get($2)");
                    pw.println(indentSpace + line + ";");
                }

                else {
                    System.out.println("⚠ Línea no reconocida: " + line);
                }
            }

            pw.println("    }");
            pw.println("}");
            System.out.println("*Traducción completada. Archivo generado: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
