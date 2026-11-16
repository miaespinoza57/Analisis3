public class PersonajeBase implements Personaje {
    private final String nombre;
    private int hp;
    private final int ataque;
    private final int defensa;
    private final int velocidad;
    private EstrategiaAtaque estrategia;

    public PersonajeBase(String nombre, int hp, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    @Override public String getNombre() { return nombre; }
    @Override public int getAtaque() { return ataque; }
    @Override public int getDefensa() { return defensa; }
    @Override public int getVelocidad() { return velocidad; }
    @Override public int getHp() { return hp; }
    @Override public void setHp(int hp) { this.hp = Math.max(0, hp); }
    @Override public EstrategiaAtaque getEstrategia() { return estrategia; }
    @Override public void setEstrategia(EstrategiaAtaque e) { this.estrategia = e; }

    @Override
    public ResultadoAtaque atacar(Personaje objetivo) {
        return estrategia.atacar(this, objetivo);
    }

    @Override
    public String getDescripcion() { return nombre + " (base)"; }
}
