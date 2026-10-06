import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Testrie {

    public static void main(String[] args) {
        Trie miTrie = new Trie();
        Scanner scanner = new Scanner(System.in);
        boolean enEjecucion = true;

        System.out.println("Iniciando sistema y cargando diccionario...");

        int palabrasCargadas = cargarDiccionario(miTrie, "diccionario.txt");
        if (palabrasCargadas > 0) {
            System.out.println("-> Exito: Se cargaron " + palabrasCargadas + " palabras del diccionario.txt");
        } else {
            System.out.println("-> Advertencia: No se pudo cargar el diccionario o el archivo esta vacio.");
        }

        System.out.println("\nBienvenido a la aplicacion de autocompletado (Trie).");

        while (enEjecucion) {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Buscar una palabra");
            System.out.println("2. Insertar una nueva palabra");
            System.out.println("3. Eliminar una palabra existente");
            System.out.println("4. Ingresar prefijo y obtener sugerencias");
            System.out.println("5. Seleccionar una sugerencia de autocompletado");
            System.out.println("6. Salir de la aplicacion");
            System.out.print("Seleccione una opcion (1-6): ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    String palabraBuscar = leerEntradaValida(scanner, "Ingrese la palabra a buscar: ");
                    if (miTrie.buscar(palabraBuscar)) {
                        System.out.println("-> La palabra '" + palabraBuscar + "' SI se encuentra en el Trie.");
                    } else {
                        System.out.println("-> La palabra '" + palabraBuscar + "' NO existe en el Trie.");
                    }
                    break;

                case "2":
                    String palabraInsertar = leerEntradaValida(scanner, "Ingrese la nueva palabra a insertar: ");
                    miTrie.insert(palabraInsertar);
                    if (miTrie.buscar(palabraInsertar)) {
                        break;
                    }
                    System.out.println("-> Palabra " + palabraInsertar + "' insertada correctamente.");
                    break;

                case "3":
                    String palabraEliminar = leerEntradaValida(scanner, "Ingrese la palabra a eliminar: ");
                    if (miTrie.buscar(palabraEliminar)) {
                        miTrie.eliminar(palabraEliminar);
                        System.out.println("-> Palabra '" + palabraEliminar + "' eliminada del Trie.");
                    } else {
                        System.out.println("-> Error: La palabra '" + palabraEliminar + "' no existe, no se puede eliminar.");
                    }
                    break;

                case "4":
                    String prefijoSugerencias = leerEntradaValida(scanner, "Ingrese el prefijo para buscar sugerencias: ");
                    List<String> sugerencias = miTrie.Autocompletar(prefijoSugerencias);

                    if (sugerencias.isEmpty()) {
                        System.out.println("-> No hay sugerencias para el prefijo '" + prefijoSugerencias + "'.");
                    } else {
                        System.out.println("-> Sugerencias encontradas:");
                        for (String s : sugerencias) {
                            System.out.println("   - " + s);
                        }
                    }
                    break;

                case "5":
                    String prefijoSeleccion = leerEntradaValida(scanner, "Ingrese el prefijo a autocompletar: ");
                    List<String> listaOpciones = miTrie.Autocompletar(prefijoSeleccion);

                    if (listaOpciones.isEmpty()) {
                        System.out.println("-> No hay palabras que comiencen con '" + prefijoSeleccion + "'.");
                    } else {
                        System.out.println("-> Seleccione el numero de la palabra que desea autocompletar:");
                        for (int i = 0; i < listaOpciones.size(); i++) {
                            System.out.println("   " + (i + 1) + ". " + listaOpciones.get(i));
                        }

                        System.out.print("Opcion elegida: ");
                        String seleccion = scanner.nextLine().trim();

                        try {
                            int indiceOpcion = Integer.parseInt(seleccion) - 1;
                            if (indiceOpcion >= 0 && indiceOpcion < listaOpciones.size()) {
                                System.out.println("\n=> Has autocompletado con exito la palabra: [" + listaOpciones.get(indiceOpcion) + "]");
                            } else {
                                System.out.println("-> Error: Numero fuera de rango.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("-> Error: Debe ingresar un numero valido.");
                        }
                    }
                    break;

                case "6":
                    System.out.println("Saliendo de la aplicacion... ¡Hasta pronto!");
                    enEjecucion = false;
                    break;

                default:
                    System.out.println("-> Opcion no valida. Por favor, ingrese un numero del 1 al 6.");
                    break;
            }
        }
        scanner.close();
    }

    private static int cargarDiccionario(Trie trie, String rutaArchivo) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String palabra = linea.trim();
                if (!palabra.isEmpty() && palabra.matches("^[A-Z]+$")) {
                    trie.insert(palabra);
                    contador++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al intentar leer el archivo: " + e.getMessage());
            System.out.println("Asegurese de que el archivo '" + rutaArchivo + "' este en la raiz del proyecto.");
        }
        return contador;
    }

    private static String leerEntradaValida(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("   [!] Error: La entrada no puede estar vacia. Intente nuevamente.");
            } else if (!entrada.matches("^[A-Z]+$")) {
                System.out.println("   [!] Error: Solo se permiten letras mayusculas de la A a la Z sin espacios ni simbolos.");
            } else {
                return entrada;
            }
        }
    }
}