/** Strategy: forma de atacar. El motor de combate nunca conoce las implementaciones concretas. */
public interface EstrategiaAtaque {
    ResultadoAtaque atacar(Personaje atacante, Personaje objetivo);
}
