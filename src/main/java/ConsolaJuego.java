import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Interfaz de consola (cliente). Solo conoce a los creadores (Factory Method),
 * a la interfaz Personaje y a los decoradores que el jugador elige para equipar.
 */
public class ConsolaJuego {
    private static final String[] CLASES = {"Guerrero", "Mago", "Arquero", "Pícaro"};
    private static final String[] EQUIPO = {"Espada", "Armadura", "Veneno", "Amuleto de regeneración", "Bendición"};
    private static final String[] ENEMIGOS = {"Goblin", "Orco", "Dragón"};

    private final Scanner entrada;

    public ConsolaJuego(Scanner entrada) {
        this.entrada = entrada;
    }

    // ------------------------------------------------------------------
    // Modo interactivo
    // ------------------------------------------------------------------
    public void iniciar() {
        System.out.println("=== RPG POR TURNOS ===");
        try {
            boolean otra = true;
            while (otra) {
                CreadorPersonaje creador = null;
                Personaje heroe = null;
                while (heroe == null) {
                    String nombre = leer("Nombre: ");
                    if (creador == null) {
                        int clase = leerOpcion("Clase " + menu(CLASES) + ": ", 1, CLASES.length);
                        creador = creadorPersonaje(clase);
                    }
                    try {
                        heroe = creador.crearPersonaje(nombre);
                    } catch (IllegalArgumentException e) {
                        System.out.println("  " + e.getMessage());
                    }
                }
                int[] equipamiento = leerEquipamiento();
                int enemigo = leerOpcion("Enemigo " + menu(ENEMIGOS) + ": ", 1, ENEMIGOS.length);

                combatir(equipar(heroe, equipamiento), creadorEnemigo(enemigo).crearEnemigo());

                otra = leer("\n¿Jugar otra vez? (s/n): ").trim().equalsIgnoreCase("s");
                System.out.println();
            }
        } catch (NoSuchElementException e) {
            System.out.println("\n(Entrada finalizada)");
        }
    }

    // ------------------------------------------------------------------
    // Modo demostración: mismas rutas de código, entradas predefinidas
    // ------------------------------------------------------------------
    public void demo() {
        corridaDemo(1, "Aria", 2, new int[]{1, 3, 5}, 1);
        corridaDemo(2, "Thorin", 1, new int[]{2, 4, 1}, 2);
        corridaDemo(3, "Legolas", 3, new int[]{3, 5, 2, 4}, 3);
        corridaDemo(4, "Sombra", 4, new int[]{1, 3, 4}, 2);
    }

    private void corridaDemo(int numero, String nombre, int clase, int[] equipo, int enemigo) {
        System.out.println("##########################################################");
        System.out.println("CORRIDA " + numero);
        System.out.println("##########################################################");
        System.out.println("Entrada simulada");
        System.out.println("Nombre: " + nombre);
        System.out.println("Clase: " + clase + " (" + CLASES[clase - 1] + ")");
        System.out.println("Equipamiento: " + listaCodigos(equipo) + " (" + listaNombres(equipo) + ")");
        System.out.println("Enemigo: " + enemigo + " (" + ENEMIGOS[enemigo - 1] + ")");
        System.out.println("\nSalida en consola");

        Personaje heroe = creadorPersonaje(clase).crearPersonaje(nombre);   // Factory Method
        combatir(equipar(heroe, equipo), creadorEnemigo(enemigo).crearEnemigo());
        System.out.println("\n");
    }

    // ------------------------------------------------------------------
    // Lógica común
    // ------------------------------------------------------------------
    private void combatir(Personaje heroe, Personaje enemigo) {
        System.out.println("Héroe: " + heroe.getDescripcion());
        System.out.println("ATK: " + heroe.getAtaque()
                + " | DEF: " + heroe.getDefensa()
                + " | VEL: " + heroe.getVelocidad()
                + " | HP: " + heroe.getHp());
        System.out.println("Enemigo: " + enemigo.getNombre() + " | HP: " + enemigo.getHp());
        System.out.println();
        new MotorCombate().iniciarCombate(heroe, enemigo);                  // Strategy dentro del combate
    }

    /** Decorator: envuelve al personaje con cada objeto elegido, en el orden dado. */
    private Personaje equipar(Personaje personaje, int[] codigos) {
        for (int codigo : codigos) {
            switch (codigo) {
                case 1: personaje = new EspadaDecorator(personaje); break;
                case 2: personaje = new ArmaduraDecorator(personaje); break;
                case 3: personaje = new VenenoDecorator(personaje); break;
                case 4: personaje = new AmuletoRegeneracionDecorator(personaje); break;
                case 5: personaje = new BendicionDecorator(personaje); break;
                default: throw new IllegalArgumentException("Equipamiento desconocido: " + codigo);
            }
        }
        return personaje;
    }

    private CreadorPersonaje creadorPersonaje(int clase) {
        switch (clase) {
            case 1: return new CreadorGuerrero();
            case 2: return new CreadorMago();
            case 3: return new CreadorArquero();
            case 4: return new CreadorPicaro();
            default: throw new IllegalArgumentException("Clase desconocida: " + clase);
        }
    }

    private CreadorEnemigo creadorEnemigo(int enemigo) {
        switch (enemigo) {
            case 1: return new CreadorGoblin();
            case 2: return new CreadorOrco();
            case 3: return new CreadorDragon();
            default: throw new IllegalArgumentException("Enemigo desconocido: " + enemigo);
        }
    }

    // ------------------------------------------------------------------
    // Lectura de datos
    // ------------------------------------------------------------------
    private String leer(String mensaje) {
        System.out.print(mensaje);
        if (!entrada.hasNextLine()) throw new NoSuchElementException();
        return entrada.nextLine();
    }

    private int leerOpcion(String mensaje, int min, int max) {
        while (true) {
            String texto = leer(mensaje).trim();
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= min && valor <= max) return valor;
            } catch (NumberFormatException ignorado) {
                // se vuelve a preguntar
            }
            System.out.println("  Opción inválida. Elige un número entre " + min + " y " + max + ".");
        }
    }

    private int[] leerEquipamiento() {
        String mensaje = "Equipamiento (códigos separados por coma, en el orden a equipar; vacío = ninguno) "
                + menu(EQUIPO) + ": ";
        while (true) {
            String texto = leer(mensaje).trim();
            if (texto.isEmpty() || texto.equals("0")) return new int[0];
            List<Integer> codigos = new ArrayList<>();
            boolean valido = true;
            for (String parte : texto.split(",")) {
                try {
                    int c = Integer.parseInt(parte.trim());
                    if (c < 1 || c > EQUIPO.length) valido = false;
                    codigos.add(c);
                } catch (NumberFormatException e) {
                    valido = false;
                }
            }
            if (valido) {
                int[] resultado = new int[codigos.size()];
                for (int i = 0; i < resultado.length; i++) resultado[i] = codigos.get(i);
                return resultado;
            }
            System.out.println("  Entrada inválida. Usa números del 1 al " + EQUIPO.length + " separados por coma.");
        }
    }

    // ------------------------------------------------------------------
    // Utilidades de presentación
    // ------------------------------------------------------------------
    private String menu(String[] opciones) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < opciones.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(i + 1).append("=").append(opciones[i]);
        }
        return sb.append(")").toString();
    }

    private String listaCodigos(int[] codigos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codigos.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(codigos[i]);
        }
        return sb.toString();
    }

    private String listaNombres(int[] codigos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codigos.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(EQUIPO[codigos[i] - 1]);
        }
        return sb.toString();
    }
}
