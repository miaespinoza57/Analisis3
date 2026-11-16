/** Decorador de ATRIBUTOS: +8 defensa. */
public class ArmaduraDecorator extends PersonajeDecorator {
    private static final int BONO_DEFENSA = 8;

    public ArmaduraDecorator(Personaje personaje) { super(personaje); }

    @Override public int getDefensa() { return super.getDefensa() + BONO_DEFENSA; }

    @Override public String getDescripcion() { return super.getDescripcion() + " + Armadura"; }
}
