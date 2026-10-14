package modelDades;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

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
    //@Default(Rang.BRONZE)
    private Rang rang;
    
    @Positive(message = "Els trofeus han de ser positius")
    @Column(name="trofeus", nullable=false)
	private int trofeus = 0;
    
    @Min(value = 0, message = "La vida ha d'estar entre 0 i 10")
    @Max(value = 10, message = "La vida ha d'estar entre 0 i 10")
    @Column(name="vida", nullable=false)
	private double vida;

	private Classe classe;
	private Partida partidaActiva;
	private Set<Partida> partidesCompletades = new HashSet<>();
}
