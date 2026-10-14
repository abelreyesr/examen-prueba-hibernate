package modelDades;

import jakarta.persistence.Entity;

/*
 * Subclasse de Partida segons l'enunciat.
 */
@Entity //revisar si se pone algo aqui como el dynamicinsert
public class PartidaRanked extends Partida {
    public PartidaRanked() {}
    public PartidaRanked(String titol, String descripcio, int recompensaTrofeus, double costVida) {
        super(titol, descripcio, recompensaTrofeus, costVida);
    }
    
    //revisar si hace falta toda la paranolla de los getters setters hashcode etc etc
}