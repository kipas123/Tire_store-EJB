package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tireproduct_has_order database table.
 * 
 */
@Embeddable
public class TireproductHasOrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="tireproduct_idtire", insertable=false, updatable=false)
	private int tireproductIdtire;

	@Column(name="order_idorder", insertable=false, updatable=false)
	private int orderIdorder;

	public TireproductHasOrderPK() {
	}
	public int getTireproductIdtire() {
		return this.tireproductIdtire;
	}
	public void setTireproductIdtire(int tireproductIdtire) {
		this.tireproductIdtire = tireproductIdtire;
	}
	public int getOrderIdorder() {
		return this.orderIdorder;
	}
	public void setOrderIdorder(int orderIdorder) {
		this.orderIdorder = orderIdorder;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TireproductHasOrderPK)) {
			return false;
		}
		TireproductHasOrderPK castOther = (TireproductHasOrderPK)other;
		return 
			(this.tireproductIdtire == castOther.tireproductIdtire)
			&& (this.orderIdorder == castOther.orderIdorder);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tireproductIdtire;
		hash = hash * prime + this.orderIdorder;
		
		return hash;
	}
}