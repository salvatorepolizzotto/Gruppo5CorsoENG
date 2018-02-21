package it.eng.projectwork.gruppo05.model;

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

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROD_ID")
	@SequenceGenerator(name="PROD_ID", sequenceName="PROD_ID", allocationSize=1)
	private long id;
	
	@Column(name="PROD_DESCR")
	private String description;
	
	//Costruttori
	
	public Product(){}
	
	public Product(long id, String descr) {
		this.id = id;
		this.description = descr;
	}
	
	//Getters e Setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + "]";
	}
	
	//private List<Image> imageList = new ArrayList<>();
}
