package modelDades;

import jakarta.persistence.Entity;

/*
 * Subclasse de Partida segons l'enunciat.
 */
@Entity
public class PartidaRanked extends Partida {
    public PartidaRanked() {}
    public PartidaRanked(String titol, String descripcio, int recompensaTrofeus, double costVida) {
        super(titol, descripcio, recompensaTrofeus, costVida);
    }
}