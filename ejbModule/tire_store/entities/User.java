package tire_store.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	private String city;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	private String housenumber;

	@Column(name="lastmodified_by")
	private String lastmodifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lastmodified_date")
	private Date lastmodifiedDate;

	private String login;

	private String name;

	private String password;

	private int phoneNumber;

	private String street;

	private String surname;

	private String zipcode;

	//bi-directional many-to-one association to Storeorder
	@OneToMany(mappedBy="user")
	private List<Storeorder> storeorders;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roles_idroles")
	private Role role;

	public User() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<Storeorder> getStoreorders() {
		return this.storeorders;
	}

	public void setStoreorders(List<Storeorder> storeorders) {
		this.storeorders = storeorders;
	}

	public Storeorder addStoreorder(Storeorder storeorder) {
		getStoreorders().add(storeorder);
		storeorder.setUser(this);

		return storeorder;
	}

	public Storeorder removeStoreorder(Storeorder storeorder) {
		getStoreorders().remove(storeorder);
		storeorder.setUser(null);

		return storeorder;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}