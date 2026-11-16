/**
 * Decorador de ATRIBUTOS y de turno (atacar): +3 defensa y +2 HP por turno de quien lo porta.
 * La regeneración nunca supera el HP que tenía el personaje al equiparlo.
 */
public class AmuletoRegeneracionDecorator extends PersonajeDecorator {
    private static final int BONO_DEFENSA = 3;
    private static final int REGENERACION = 2;

    private final int hpMaximo;

    public AmuletoRegeneracionDecorator(Personaje personaje) {
        super(personaje);
        this.hpMaximo = personaje.getHp();
    }

    @Override public int getDefensa() { return super.getDefensa() + BONO_DEFENSA; }

    @Override
    protected ResultadoAtaque modificarResultado(ResultadoAtaque r, Personaje objetivo) {
        int antes = getHp();
        setHp(Math.min(hpMaximo, antes + REGENERACION));
        int curado = getHp() - antes;
        if (curado <= 0) return r;
        return new ResultadoAtaque(r.getDano(), r.isCritico(),
                r.getEfecto() + " + regeneración (+" + curado + " HP)");
    }

    @Override public String getDescripcion() { return super.getDescripcion() + " + Amuleto de Regeneración"; }
}
