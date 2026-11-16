/** Espadazo: daño = ataque - defensa (mínimo 1). Cada 3er golpe es crítico (x1.5). */
public class AtaqueEspada implements EstrategiaAtaque {
    private int golpes = 0;

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        golpes++;
        int dano = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        boolean critico = golpes % 3 == 0;
        if (critico) dano = (int) Math.round(dano * 1.5);
        return new ResultadoAtaque(dano, critico, "espada");
    }
}
