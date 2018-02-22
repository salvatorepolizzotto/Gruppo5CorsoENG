package it.eng.projectwork.gruppo05.model;

import javax.persistence.Column;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Version;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
	
	
	@Column(name="TITLE", nullable = false)
	private String title;
	
	@ManyToOne
	@JoinColumn(name="AUCT_SUPP")
	private Supplier supplier;   //Venditore che mette l'asta.
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="AUCT_PROD")
	private Product product;                     //Prodotto messo all'asta
	
	@Column(name="AUCT_BEGIN", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionBeginning;
	
	@Column(name="AUCT_END")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionEnding;
	
	@Column(name="LAST_BID_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastBidDate;
	
	//Lista di offerte 
	@Column(name="AUCT_BID")
	@OneToMany(mappedBy="auction", cascade = CascadeType.ALL)
	private List<Bid> auctionBids= new ArrayList<Bid>();
	
	@Column(name="SUSPENDED")
	private boolean suspended;
	
	@Enumerated(EnumType.STRING)
	private PRICING pricing;
	
	//VERSION
	
	@Version
	private long version;
	
	//Costruttori
	
	public Auction(String title, Product prod, Date beginDate, Date endDate) {
		this.title = title;
		this.product = prod;
		this.setRangeAuction(beginDate, endDate);
	}
	
	public Auction() {}
	
	public Auction(String title, Supplier supplier, Product product, boolean suspended, PRICING pricing, long version) {
		this.title = title;
		this.supplier = supplier;
		this.product = product;
		setRangeAuction(auctionBeginning, auctionEnding);
		this.suspended = suspended;
		this.pricing = pricing;
		this.version = version;
	}

	//GETTERS AND SETTERS
	
	public void setSuspended(boolean susp) {
		this.suspended = susp;
	}

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

	public List<Bid> getAuctionBids() {
		return auctionBids;
	}

	public void setAuctionBids(List<Bid> auctionBids) {
		this.auctionBids = auctionBids;
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
	
	public PRICING getPricing() {
		return pricing;
	}

	//METODI
	
	//Per settare il periodo di attività dell'asta
	public void setRangeAuction(Date startDate,Date endDate) {
		if(startDate!=null && endDate!=null && startDate.getTime() < endDate.getTime() &&  startDate.getTime() > System.currentTimeMillis() && endDate.getTime() > System.currentTimeMillis()){
			this.auctionBeginning = startDate;
			this.auctionEnding = endDate;
		}else{
			throw new AuctionRangeDateNotValidException("start date and enda date must be future");
		}
	}
	
	@Transient
	//Ritorna lo STATE dell'asta
	public STATE getState() {
		return STATE.eval(this);
	}
	
	public void AddBid(Bid newBid) throws AddBidNotValidException{
		if(getState().canAddBid()) {
			if(getPricing().canAddBid(getPricing().getBestOffer(this), newBid)) {
				newBid.setAuction(this);  //setta questa asta come campo Auction nella nuova Bid
				getAuctionBids().add(newBid);
				lastBidDate = newBid.getBidDate();		
			}else {
				throw new AddBidNotValidException("Mi dispiace! Offerta migliore ricevuta.");
			}
		}else {
			throw new AddBidNotValidException("Ops... Lo stato dell'asta non permette nuove offerte.");
		}
	}
	
	public boolean isSuspended() {
		return suspended;
	}
	
	@Override
	public String toString() {
		return "Auction [id=" + id + ", title=" + title + ", supplier=" + supplier + ", product=" + product
				+ ", auctionBeginning=" + auctionBeginning + ", auctionEnding=" + auctionEnding + ", lastBidDate="
				+ lastBidDate + ", auctionBids=" + auctionBids + ", version=" + version + "]";
	}
}