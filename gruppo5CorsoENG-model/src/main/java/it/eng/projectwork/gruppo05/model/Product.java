package it.eng.projectwork.gruppo05.model;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.*;
import java.util.Collection.*;

@Entity
@Table(name="PRODUCT")
public class Product {
	@Id
	@Column(name="PROD_ID")
	private long id;
	
	@Column(name="PROD_DESCR")
	private String description;

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
