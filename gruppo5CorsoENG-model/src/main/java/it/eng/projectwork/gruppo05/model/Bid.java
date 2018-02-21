package it.eng.projectwork.gruppo05.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import java.io.Serializable;
import javax.persistence.Transient;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import java.awt.*;
import java.util.Collection.*;
import javax.persistence.CascadeType;
import java.util.Collection;
import javax.persistence.Version;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.math.BigDecimal;

@Entity
@Table(name="BID")
public class Bid {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BID_ID")
	@SequenceGenerator(name="BID_ID", sequenceName="BID_ID", allocationSize=1)
	private long id;
	
	@Column(name="BID_AUCTION")
	@ManyToOne
	private Auction auction;
	
	@Column(name="PRICE")
	private BigDecimal price;
	
	@Column(name="BID_USER")
	@ManyToOne
	private User user;
	
	@Column(name="BID_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bidDate;
	
	//Costruttori
	
	public Bid() {}
	
	public Bid(User us, BigDecimal price) {
		this.user = us;
		this.price = price;
		this.bidDate = new Timestamp(System.currentTimeMillis());
	}
	
	//Getters e Setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBidDate() {
		return bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}
	
}
