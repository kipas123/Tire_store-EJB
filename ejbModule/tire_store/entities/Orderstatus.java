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

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="orderstatus")
	private List<Order> orders;

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

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderstatus(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderstatus(null);

		return order;
	}

}