package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the storeorder database table.
 * 
 */
@Entity
@NamedQuery(name="Storeorder.findAll", query="SELECT s FROM Storeorder s")
public class Storeorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idorder;

	private String city;

	private String housenumber;

	@Column(name="lastmodified_by")
	private String lastmodifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lastmodified_date")
	private Date lastmodifiedDate;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderdata;

	private int phoneNumber;

	private String street;

	private String surname;

	private double totalprice;

	private String zipcode;

	//bi-directional many-to-one association to Orderstatus
	@ManyToOne
	private Orderstatus orderstatus;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to TireproductHasOrder
	@OneToMany(mappedBy="storeorder")
	private List<TireproductHasOrder> tireproductHasOrders;

	public Storeorder() {
	}

	public int getIdorder() {
		return this.idorder;
	}

	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHousenumber() {
		return this.housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getLastmodifiedBy() {
		return this.lastmodifiedBy;
	}

	public void setLastmodifiedBy(String lastmodifiedBy) {
		this.lastmodifiedBy = lastmodifiedBy;
	}

	public Date getLastmodifiedDate() {
		return this.lastmodifiedDate;
	}

	public void setLastmodifiedDate(Date lastmodifiedDate) {
		this.lastmodifiedDate = lastmodifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getOrderdata() {
		return this.orderdata;
	}

	public void setOrderdata(Date orderdata) {
		this.orderdata = orderdata;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public double getTotalprice() {
		return this.totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
		tireproductHasOrder.setStoreorder(this);

		return tireproductHasOrder;
	}

	public TireproductHasOrder removeTireproductHasOrder(TireproductHasOrder tireproductHasOrder) {
		getTireproductHasOrders().remove(tireproductHasOrder);
		tireproductHasOrder.setStoreorder(null);

		return tireproductHasOrder;
	}

}