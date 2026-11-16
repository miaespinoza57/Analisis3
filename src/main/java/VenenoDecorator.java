import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Decorador de ATRIBUTOS y de DAÑO (atacar): +5 ataque y efecto DOT.
 * El primer golpe envenena al objetivo; en los 3 ataques siguientes el veneno
 * suma +3 de daño por turno (DOT). Pasados esos turnos, el siguiente golpe vuelve a envenenar.
 */
public class VenenoDecorator extends PersonajeDecorator {
    private static final int BONO_ATAQUE = 5;
    private static final int DANO_DOT = 3;
    private static final int DURACION_DOT = 3;

    private final Map<Personaje, Integer> turnosRestantes = new IdentityHashMap<>();

    public VenenoDecorator(Personaje personaje) { super(personaje); }

    @Override public int getAtaque() { return super.getAtaque() + BONO_ATAQUE; }

    @Override
    protected ResultadoAtaque modificarResultado(ResultadoAtaque r, Personaje objetivo) {
        int turnos = turnosRestantes.getOrDefault(objetivo, 0);
        int extra = 0;
        String efecto = r.getEfecto() + " + veneno";
        if (turnos > 0) {
            extra = DANO_DOT;
            efecto += " (DOT +" + DANO_DOT + ")";
            turnosRestantes.put(objetivo, turnos - 1);
        } else {
            turnosRestantes.put(objetivo, DURACION_DOT);
        }
        return new ResultadoAtaque(r.getDano() + extra, r.isCritico(), efecto);
    }

    @Override public String getDescripcion() { return super.getDescripcion() + " + Veneno"; }
}
