/** Puñalada: daño = ataque - defensa (mínimo 1). Cada 2da puñalada es crítica (x2, por la espalda). */
public class AtaquePunalada implements EstrategiaAtaque {
    private int punaladas = 0;

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        punaladas++;
        int dano = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        boolean critico = punaladas % 2 == 0;
        if (critico) dano *= 2;
        return new ResultadoAtaque(dano, critico, "puñalada");
    }
}
