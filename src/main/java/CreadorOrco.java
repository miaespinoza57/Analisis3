public class CreadorOrco extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje e = new Enemigo("Orco brutal", 110, 22, 10, 8);
        e.setEstrategia(new AtaqueEspada());
        return e;
    }
}
