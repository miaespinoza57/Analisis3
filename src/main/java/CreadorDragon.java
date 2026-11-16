public class CreadorDragon extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje e = new Enemigo("Dragón ancestral", 150, 20, 15, 11);
        e.setEstrategia(new AtaqueHechizo());
        return e;
    }
}
