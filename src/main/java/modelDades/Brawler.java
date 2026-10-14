package modelDades;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/*
 * Completa la classe segons l'enunciat: anotacions, constructors, getters, setters
 * (amb els controls que demana l'enunciat), hashCode, equals i toString.
 * NO canvieu els noms ni els tipus dels atributs: el Main i DadesProva els fan servir.
 */
@Entity
@Table(name="brawler")
@DynamicInsert
public class Brawler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
    
	@NotBlank(message = "El nom és obligatori")
    @Column(name="nom", nullable=false, unique=true)
	private String nom;
	
    @Enumerated(EnumType.STRING)
	@Column(name="rang", nullable=false)
    private Rang rang = Rang.BRONZE;
    
    @PositiveOrZero(message = "Els trofeus han de ser positius")
    @Column(name="trofeus", nullable=false)
	private int trofeus = 0;
    
    @Min(value = 0, message = "La vida ha d'estar entre 0 i 10")
    @Max(value = 10, message = "La vida ha d'estar entre 0 i 10")
    @Column(name="vida", nullable=false)
	private double vida= 10;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "classe_id", nullable = false)
	private Classe classe;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partida_activa_id")
	private Partida partidaActiva;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "brawler_partida_completada",
            joinColumns = @JoinColumn(name = "brawler_id"),
            inverseJoinColumns = @JoinColumn(name = "partida_id"))
	private Set<Partida> partidesCompletades = new HashSet<>();
    
    
    protected Brawler() {}

    public Brawler(String nom) {
        this.nom = nom;
    }

    public Integer getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Rang getRang() { return rang; }
    public void setRang(Rang rang) {
        if (rang != null) this.rang = rang;
    }

    public int getTrofeus() { return trofeus; }
    public void setTrofeus(int trofeus) {
        this.trofeus = Math.max(0, trofeus);
    }

    public double getVida() { return vida; }
    public void setVida(double vida) {
        this.vida = Math.max(0, Math.min(10, vida));
    }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe nova) {
        if (nova == classe) return;
        if (classe != null) classe.getBrawlers().remove(this);
        classe = nova;
        if (nova != null && !nova.getBrawlers().contains(this)) {
            nova.getBrawlers().add(this);
        }
    }

    public Partida getPartidaActiva() { return partidaActiva; }
    public void setPartidaActiva(Partida nova) {
        if (nova != null && partidesCompletades.contains(nova)) return; // ja completada
        if (nova != null && nova.equals(partidaActiva)) return;         // ja és l'activa: no cobrar dues vegades
        if (partidaActiva != null) partidaActiva.getParticipants().remove(this);
        partidaActiva = nova;
        if (nova != null) {
            if (!nova.getParticipants().contains(this)) nova.getParticipants().add(this);
            setVida(vida - nova.getCostVida());
        }
    }

    public Set<Partida> getPartidesCompletades() { return partidesCompletades; }

    public void afegirPartidaCompletada(Partida p) {
        if (p == null || partidesCompletades.contains(p)) return;
        if (p.equals(partidaActiva)) {
            partidaActiva.getParticipants().remove(this);
            partidaActiva = null;
        }
        partidesCompletades.add(p);
        p.getCompletadors().add(this);
    }

    public void eliminarPartidaCompletada(Partida p) {
        if (p == null) return;
        partidesCompletades.remove(p);
        p.getCompletadors().remove(this);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(id, ((Brawler) obj).id);
    }

    @Override
    public String toString() {
        return "Brawler [id=" + id + ", nom=" + nom + ", rang=" + rang + ", trofeus=" + trofeus
                + ", vida=" + vida + ", classe=" + classe + ", partidaActiva=" + partidaActiva
                + ", partidesCompletades=" + partidesCompletades + "]";
    }
}
