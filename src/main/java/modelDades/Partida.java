package modelDades;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Completa la classe segons l'enunciat: anotacions, constructors, getters, setters
 * (amb els controls que demana l'enunciat), hashCode, equals i toString.
 * NO canvieu els noms ni els tipus dels atributs: el Main i DadesProva els fan servir.
 */
@Entity
@Table(name="partida")
@DynamicInsert
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipus")
public abstract class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
    
	@NotBlank(message = "El titol és obligatori")
    @Column(name="titol", nullable=false)
	private String titol;
	
	@NotBlank(message = "La descripcio és obligatoria")
    @Column(name="descripcio", nullable=false)
	private String descripcio;
	
    @Column(name="recompensa_trofeus", nullable=false)
	private int recompensaTrofeus;
	
    @Column(name="cost_vida", nullable=false)
	private double costVida;

    @OneToMany(mappedBy = "partidaActiva")
	private Set<Brawler> participants = new HashSet<>();
    
    @ManyToMany(mappedBy = "partidesCompletades")
	private Set<Brawler> completadors = new HashSet<>();
    
    protected Partida() {}

	public Partida(@NotBlank(message = "El titol és obligatori") String titol,
			@NotBlank(message = "La descripcio és obligatoria") String descripcio,
			@NotNull(message = "La recompensa és obligatoria") int recompensaTrofeus, double costVida) {
		super();
		this.titol = titol;
		this.descripcio = descripcio;
		this.recompensaTrofeus = recompensaTrofeus;
		this.costVida = costVida;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", titol=" + titol + ", descripcio=" + descripcio + ", recompensaTrofeus="
				+ recompensaTrofeus + ", costVida=" + costVida + ", tipus="+ Hibernate.getClass(this).getSimpleName() +"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return Objects.equals(id, other.id);
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getRecompensaTrofeus() {
		return recompensaTrofeus;
	}

	public void setRecompensaTrofeus(int recompensaTrofeus) {
		this.recompensaTrofeus = recompensaTrofeus;
	}

	public double getCostVida() {
		return costVida;
	}

	public void setCostVida(double costVida) {
		this.costVida = costVida;
	}

	public Integer getId() {
		return id;
	}

	public Set<Brawler> getParticipants() {
		return participants;
	}

	public Set<Brawler> getCompletadors() {
		return completadors;
	}


}
