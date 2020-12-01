package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the offer database table.
 * 
 */
@Entity
@NamedQuery(name="Offer.findAll", query="SELECT o FROM Offer o")
public class Offer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idoffer;

	private byte active;

	@Lob
	private String description;

	private String imghref;

	private int price;

	private int quantity;

	@Lob
	private String shortdescription;

	//bi-directional many-to-one association to Tireproduct
	@ManyToOne
	private Tireproduct tireproduct;

	public Offer() {
	}

	public int getIdoffer() {
		return this.idoffer;
	}

	public void setIdoffer(int idoffer) {
		this.idoffer = idoffer;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
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

	public Tireproduct getTireproduct() {
		return this.tireproduct;
	}

	public void setTireproduct(Tireproduct tireproduct) {
		this.tireproduct = tireproduct;
	}

}