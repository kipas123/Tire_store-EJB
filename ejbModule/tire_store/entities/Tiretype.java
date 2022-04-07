package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tiretype database table.
 * 
 */
@Entity
@NamedQuery(name="Tiretype.findAll", query="SELECT t FROM Tiretype t")
public class Tiretype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtiretype;

	private String name;

	private int sequence;

	//bi-directional many-to-one association to Tireproduct
	@OneToMany(mappedBy="tiretype")
	private List<Tireproduct> tireproducts;

	public Tiretype() {
	}

	public int getIdtiretype() {
		return this.idtiretype;
	}

	public void setIdtiretype(int idtiretype) {
		this.idtiretype = idtiretype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public List<Tireproduct> getTireproducts() {
		return this.tireproducts;
	}

	public void setTireproducts(List<Tireproduct> tireproducts) {
		this.tireproducts = tireproducts;
	}

	public Tireproduct addTireproduct(Tireproduct tireproduct) {
		getTireproducts().add(tireproduct);
		tireproduct.setTiretype(this);

		return tireproduct;
	}

	public Tireproduct removeTireproduct(Tireproduct tireproduct) {
		getTireproducts().remove(tireproduct);
		tireproduct.setTiretype(null);

		return tireproduct;
	}

}