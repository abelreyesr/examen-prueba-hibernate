package modelDades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="classe")
@DynamicInsert
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
    
	@NotBlank(message = "El nom és obligatori")
    @Column(name="nom", nullable=false, unique=true)
	private String nom;
	
	@NotBlank(message = "L'habilitat és obligatoria")
    @Column(name="habilitat", nullable=false)
	private String habilitat;
	
    @Column(name="dany", nullable=false)
	private double dany;

    @OneToMany(mappedBy = "classe")
	private List<Brawler> brawlers = new ArrayList<>();
	
    
	public Classe() {}
	
    public Classe(@NotBlank(message = "El nom és obligatori") String nom,
			@NotBlank(message = "L'habilitat és obligatoria") String habilitat,
			@NotNull(message = "Ha de tenir dany") double dany) {
		super();
		this.nom = nom;
		this.habilitat = habilitat;
		this.dany = dany;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getHabilitat() {
		return habilitat;
	}

	public void setHabilitat(String habilitat) {
		this.habilitat = habilitat;
	}

	public double getDany() {
		return dany;
	}

	public void setDany(double dany) {
		this.dany = dany;
	}

	public List<Brawler> getBrawlers() {
		return brawlers;
	}

	public Integer getId() {
		return id;
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
		Classe other = (Classe) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Classe [id=" + id + ", nom=" + nom + ", habilitat=" + habilitat + ", dany=" + dany + "]";
	}
}
