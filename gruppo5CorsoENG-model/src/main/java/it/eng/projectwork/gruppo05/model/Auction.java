package it.eng.projectwork.gruppo05.model;

import javax.persistence.Column;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Version;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
@Table(name="AUCTION")
public class Auction implements Serializable {
	
	//DATI MEMBRO AUCTION
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUCT_ID")
	@SequenceGenerator(name="AUCT_ID", sequenceName="AUCT_ID", allocationSize=1)
	private long id;
	
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="SUPPLIER")
	@ManyToOne
	private Supplier supplier;   //Venditore che mette l'asta.
	
	@Column(name="AUCT_PROD")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Product product;                     //Prodotto messo all'asta
	
	@Column(name="AUCT_BEGIN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionBeginning;
	
	@Column(name="AUCT_END")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionEnding;
	
	@Column(name="LAST_BID_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastBidDate;
	
	@Column(name="AUCT_BID")
	@OneToMany(mappedBy="auction", cascade = CascadeType.ALL)
	private List<Bid> auctionBids= new ArrayList<Bid>();
	
	//VERSION
	
	@Version
	private long version;
	
	//GETTERS AND SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getAuctionBeginning() {
		return auctionBeginning;
	}

	public void setAuctionBeginning(Date auctionBeginning) {
		this.auctionBeginning = auctionBeginning;
	}

	public Date getAuctionEnding() {
		return auctionEnding;
	}

	public void setAuctionEnding(Date auctionEnding) {
		this.auctionEnding = auctionEnding;
	}

	public Date getLastBidDate() {
		return lastBidDate;
	}

	public void setLastBidDate(Date lastBidDate) {
		this.lastBidDate = lastBidDate;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	//METODI
	
}