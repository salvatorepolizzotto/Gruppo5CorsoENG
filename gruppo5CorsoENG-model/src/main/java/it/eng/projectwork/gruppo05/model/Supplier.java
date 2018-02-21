package it.eng.projectwork.gruppo05.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;


@Entity
@Table(name="SUPPLIER")

public class Supplier extends User{
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Column(name="SUPP_INFO")
	private String info;

	@Override
	public String toString() {
		return "Supplier [" + this.getUsername() + " info= " + info + "]";
	}
}
