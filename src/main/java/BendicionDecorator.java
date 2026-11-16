/**
 * Decorador de ATRIBUTOS: +15% a ataque, defensa y velocidad (lo que ya tenga acumulado
 * el personaje envuelto, por eso el orden de equipamiento importa). El HP no se modifica.
 */
public class BendicionDecorator extends PersonajeDecorator {
    public BendicionDecorator(Personaje personaje) { super(personaje); }

    // Aritmética entera para redondear half-up sin errores de coma flotante: v * 1.15
    private int bonificar(int valor) { return (valor * 115 + 50) / 100; }

    @Override public int getAtaque() { return bonificar(super.getAtaque()); }
    @Override public int getDefensa() { return bonificar(super.getDefensa()); }
    @Override public int getVelocidad() { return bonificar(super.getVelocidad()); }

    @Override public String getDescripcion() { return super.getDescripcion() + " + Bendición"; }
}
