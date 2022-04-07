package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tireproduct_has_order database table.
 * 
 */
@Entity
@Table(name="tireproduct_has_order")
@NamedQuery(name="TireproductHasOrder.findAll", query="SELECT t FROM TireproductHasOrder t")
public class TireproductHasOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TireproductHasOrderPK id;

	private int quantity;

	//bi-directional many-to-one association to Storeorder
	@ManyToOne
	@JoinColumn(name="order_idorder", insertable=false, updatable=false)
	private Storeorder storeorder;

	//bi-directional many-to-one association to Tireproduct
	@ManyToOne
	@JoinColumn(name = "tireproduct_idtire", insertable=false, updatable=false)
	private Tireproduct tireproduct;

	public TireproductHasOrder() {
	}

	public TireproductHasOrderPK getId() {
		return this.id;
	}

	public void setId(TireproductHasOrderPK id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Storeorder getStoreorder() {
		return this.storeorder;
	}

	public void setStoreorder(Storeorder storeorder) {
		this.storeorder = storeorder;
	}

	public Tireproduct getTireproduct() {
		return this.tireproduct;
	}

	public void setTireproduct(Tireproduct tireproduct) {
		this.tireproduct = tireproduct;
	}

}