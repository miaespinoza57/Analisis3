/** Hechizo: daño mágico = ataque; ignora la defensa. Nunca es crítico. */
public class AtaqueHechizo implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        return new ResultadoAtaque(Math.max(1, atacante.getAtaque()), false, "hechizo");
    }
}
