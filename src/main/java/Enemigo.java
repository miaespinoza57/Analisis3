/** Personaje enemigo: igual que PersonajeBase pero su descripción es solo su nombre. */
public class Enemigo extends PersonajeBase {
    public Enemigo(String nombre, int hp, int ataque, int defensa, int velocidad) {
        super(nombre, hp, ataque, defensa, velocidad);
    }

    @Override
    public String getDescripcion() { return getNombre(); }
}
