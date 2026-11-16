public class CreadorArquero extends CreadorPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        Personaje p = new PersonajeBase(nombre, 90, 22, 8, 18);
        p.setEstrategia(new AtaqueFlecha());
        return p;
    }
}
