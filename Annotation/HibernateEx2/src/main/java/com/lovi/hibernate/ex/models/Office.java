package com.lovi.hibernate.ex.models;

import javax.persistence.Embeddable;

@Embeddable
public class Office {

	private String officeName;

	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	
}
