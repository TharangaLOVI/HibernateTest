package com.lovi.hibernate.ex.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="person_tbl")
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="person_id")
	private int personId;
	
	@Column(name="person_name")
	private String personName;
	
	/**
	 * Collection are supporting for indexing
	 * @ElementCollection => fetch=FetchType.EAGER mean fetch all office list when person object get
	 * 							default is  fetch=FetchType.LAZY
	 * @JoinTable => relation data handle ,table(employee_offices),forigen key(emp_id)
	 *
	 */
	@ElementCollection
	@JoinTable(name="person_offices",joinColumns=@JoinColumn(name="person_id"))
	private Collection<Office> offices = new ArrayList<Office>();

	public int getEmpId() {
		return personId;
	}

	public void setEmpId(int empId) {
		this.personId = empId;
	}

	public String getEmpName() {
		return personName;
	}

	public void setEmpName(String empName) {
		this.personName = empName;
	}

	public Collection<Office> getOffices() {
		return offices;
	}

	public void setOffices(Collection<Office> offices) {
		this.offices = offices;
	}
	
}
