/** Decorador de ATRIBUTOS: +10 ataque. */
public class EspadaDecorator extends PersonajeDecorator {
    private static final int BONO_ATAQUE = 10;

    public EspadaDecorator(Personaje personaje) { super(personaje); }

    @Override public int getAtaque() { return super.getAtaque() + BONO_ATAQUE; }

    @Override public String getDescripcion() { return super.getDescripcion() + " + Espada"; }
}
