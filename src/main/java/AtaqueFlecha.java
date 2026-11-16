/** Flecha: daño = ataque - defensa/2 (mínimo 1). Crítico (x1.5) si supera al objetivo en velocidad por 8 o más. */
public class AtaqueFlecha implements EstrategiaAtaque {
    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, atacante.getAtaque() - objetivo.getDefensa() / 2);
        boolean critico = atacante.getVelocidad() >= objetivo.getVelocidad() + 8;
        if (critico) dano = (int) Math.round(dano * 1.5);
        return new ResultadoAtaque(dano, critico, "flecha");
    }
}
