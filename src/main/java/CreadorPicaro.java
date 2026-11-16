public class CreadorPicaro extends CreadorPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        Personaje p = new PersonajeBase(nombre, 85, 20, 7, 20);
        p.setEstrategia(new AtaquePunalada());
        return p;
    }
}
