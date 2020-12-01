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

	private String brand;

	@Column(name="has_offer")
	private byte hasOffer;

	private String model;

	private String size;

	//bi-directional many-to-one association to Offer
	@OneToMany(mappedBy="tireproduct")
	private List<Offer> offers;

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

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public byte getHasOffer() {
		return this.hasOffer;
	}

	public void setHasOffer(byte hasOffer) {
		this.hasOffer = hasOffer;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Offer addOffer(Offer offer) {
		getOffers().add(offer);
		offer.setTireproduct(this);

		return offer;
	}

	public Offer removeOffer(Offer offer) {
		getOffers().remove(offer);
		offer.setTireproduct(null);

		return offer;
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