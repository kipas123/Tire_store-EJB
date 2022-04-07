package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tireproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Tireproduct.findAll", query="SELECT t FROM Tireproduct t")
public class Tireproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtire;

	private byte active;

	private byte archived;

	private String brand;

	private String description;

	private String imghref;

	private String model;

	private double price;

	private int quantity;

	private String shortdescription;

	private String size;

	//bi-directional many-to-one association to Tiretype
	@ManyToOne
	private Tiretype tiretype;

	//bi-directional many-to-one association to TireproductHasOrder
	@OneToMany(mappedBy="tireproduct")
	private List<TireproductHasOrder> tireproductHasOrders;

	public Tireproduct() {
	}

	public int getIdtire() {
		return this.idtire;
	}

	public void setIdtire(int idtire) {
		this.idtire = idtire;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public byte getArchived() {
		return this.archived;
	}

	public void setArchived(byte archived) {
		this.archived = archived;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImghref() {
		return this.imghref;
	}

	public void setImghref(String imghref) {
		this.imghref = imghref;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShortdescription() {
		return this.shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Tiretype getTiretype() {
		return this.tiretype;
	}

	public void setTiretype(Tiretype tiretype) {
		this.tiretype = tiretype;
	}

	public List<TireproductHasOrder> getTireproductHasOrders() {
		return this.tireproductHasOrders;
	}

	public void setTireproductHasOrders(List<TireproductHasOrder> tireproductHasOrders) {
		this.tireproductHasOrders = tireproductHasOrders;
	}

	public TireproductHasOrder addTireproductHasOrder(TireproductHasOrder tireproductHasOrder) {
		getTireproductHasOrders().add(tireproductHasOrder);
		tireproductHasOrder.setTireproduct(this);

		return tireproductHasOrder;
	}

	public TireproductHasOrder removeTireproductHasOrder(TireproductHasOrder tireproductHasOrder) {
		getTireproductHasOrders().remove(tireproductHasOrder);
		tireproductHasOrder.setTireproduct(null);

		return tireproductHasOrder;
	}

}