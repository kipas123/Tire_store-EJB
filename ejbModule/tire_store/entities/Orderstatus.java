package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orderstatus database table.
 * 
 */
@Entity
@NamedQuery(name="Orderstatus.findAll", query="SELECT o FROM Orderstatus o")
public class Orderstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idorderstatus;

	private String name;

	private int sequence;

	//bi-directional many-to-one association to Storeorder
	@OneToMany(mappedBy="orderstatus")
	private List<Storeorder> storeorders;

	public Orderstatus() {
	}

	public int getIdorderstatus() {
		return this.idorderstatus;
	}

	public void setIdorderstatus(int idorderstatus) {
		this.idorderstatus = idorderstatus;
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

	public List<Storeorder> getStoreorders() {
		return this.storeorders;
	}

	public void setStoreorders(List<Storeorder> storeorders) {
		this.storeorders = storeorders;
	}

	public Storeorder addStoreorder(Storeorder storeorder) {
		getStoreorders().add(storeorder);
		storeorder.setOrderstatus(this);

		return storeorder;
	}

	public Storeorder removeStoreorder(Storeorder storeorder) {
		getStoreorders().remove(storeorder);
		storeorder.setOrderstatus(null);

		return storeorder;
	}

}