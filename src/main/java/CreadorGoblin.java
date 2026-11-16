public class CreadorGoblin extends CreadorEnemigo {
    @Override
    public Personaje crearEnemigo() {
        Personaje e = new Enemigo("Goblin salvaje", 60, 10, 4, 12);
        e.setEstrategia(new AtaquePunalada());
        return e;
    }
}
