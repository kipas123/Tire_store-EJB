package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idorder;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderdata;

	private int totalprice;

	//bi-directional many-to-one association to Orderstatus
	@ManyToOne
	private Orderstatus orderstatus;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to TireproductHasOrder
	@OneToMany(mappedBy="order")
	private List<TireproductHasOrder> tireproductHasOrders;

	public Order() {
	}

	public int getIdorder() {
		return this.idorder;
	}

	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}

	public Date getOrderdata() {
		return this.orderdata;
	}

	public void setOrderdata(Date orderdata) {
		this.orderdata = orderdata;
	}

	public int getTotalprice() {
		return this.totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public Orderstatus getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TireproductHasOrder> getTireproductHasOrders() {
		return this.tireproductHasOrders;
	}

	public void setTireproductHasOrders(List<TireproductHasOrder> tireproductHasOrders) {
		this.tireproductHasOrders = tireproductHasOrders;
	}

	public TireproductHasOrder addTireproductHasOrder(TireproductHasOrder tireproductHasOrder) {
		getTireproductHasOrders().add(tireproductHasOrder);
		tireproductHasOrder.setOrder(this);

		return tireproductHasOrder;
	}

	public TireproductHasOrder removeTireproductHasOrder(TireproductHasOrder tireproductHasOrder) {
		getTireproductHasOrders().remove(tireproductHasOrder);
		tireproductHasOrder.setOrder(null);

		return tireproductHasOrder;
	}

}