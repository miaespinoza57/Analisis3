import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    /**
     * Sin argumentos: juego interactivo por consola.
     * Con el argumento --demo: ejecuta las corridas de ejemplo (las de EJECUCION.txt).
     */
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        ConsolaJuego consola = new ConsolaJuego(new Scanner(System.in, StandardCharsets.UTF_8));

        if (args.length > 0 && args[0].equals("--demo")) {
            consola.demo();
        } else {
            consola.iniciar();
        }
    }
}
