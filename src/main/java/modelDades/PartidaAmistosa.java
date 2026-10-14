package modelDades;

import jakarta.persistence.Entity;

/*
 * Subclasse de Partida segons l'enunciat.
 */
@Entity //revisar si se pone algo aqui como el dynamicinsert
public class PartidaAmistosa extends Partida {
    public PartidaAmistosa() {}
    public PartidaAmistosa(String titol, String descripcio, int recompensaTrofeus, double costVida) {
        super(titol, descripcio, recompensaTrofeus, costVida);
    }
}