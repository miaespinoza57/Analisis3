/**
 * Decorator: envuelve a un Personaje para modificar atributos y/o el resultado de atacar().
 *
 * Nota de diseño sobre atacar(): PersonajeBase.atacar() llama a estrategia.atacar(this, ...),
 * donde "this" es el personaje BASE (sin bonificaciones). Si el decorador solo delegara hacia
 * adentro, la estrategia leería los atributos sin decorar y el equipamiento no tendría efecto
 * en el daño. Por eso el decorador más externo invoca la estrategia pasándose a sí mismo
 * (los getters ya incluyen todas las capas) y después cada capa, de adentro hacia afuera,
 * puede ajustar el resultado mediante el gancho modificarResultado().
 */
public abstract class PersonajeDecorator implements Personaje {
    protected final Personaje personaje;

    public PersonajeDecorator(Personaje personaje) {
        this.personaje = personaje;
    }

    @Override public String getNombre() { return personaje.getNombre(); }
    @Override public int getAtaque() { return personaje.getAtaque(); }
    @Override public int getDefensa() { return personaje.getDefensa(); }
    @Override public int getVelocidad() { return personaje.getVelocidad(); }
    @Override public int getHp() { return personaje.getHp(); }
    @Override public void setHp(int hp) { personaje.setHp(hp); }
    @Override public EstrategiaAtaque getEstrategia() { return personaje.getEstrategia(); }
    @Override public void setEstrategia(EstrategiaAtaque e) { personaje.setEstrategia(e); }

    @Override
    public ResultadoAtaque atacar(Personaje objetivo) {
        ResultadoAtaque resultado = getEstrategia().atacar(this, objetivo);
        return aplicarEfectos(resultado, objetivo);
    }

    private ResultadoAtaque aplicarEfectos(ResultadoAtaque resultado, Personaje objetivo) {
        if (personaje instanceof PersonajeDecorator) {
            resultado = ((PersonajeDecorator) personaje).aplicarEfectos(resultado, objetivo);
        }
        return modificarResultado(resultado, objetivo);
    }

    /** Gancho: los decoradores que alteran el daño o el efecto del ataque lo sobrescriben. */
    protected ResultadoAtaque modificarResultado(ResultadoAtaque resultado, Personaje objetivo) {
        return resultado;
    }

    @Override
    public String getDescripcion() { return personaje.getDescripcion(); }
}
