public interface Personaje {
    String getNombre();
    int getAtaque();
    int getDefensa();
    int getVelocidad();
    int getHp();
    void setHp(int hp);
    EstrategiaAtaque getEstrategia();
    void setEstrategia(EstrategiaAtaque estrategia);
    ResultadoAtaque atacar(Personaje objetivo);
    String getDescripcion();
}
