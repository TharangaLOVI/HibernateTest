package com.lovi.hibernate.ex.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
@Table(name="employee_tbl")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="emp_name")
	private String empName;
	
	/**
	 * Collection are supporting for indexing
	 * @JoinTable => relation data handle ,table(employee_offices),forigen key(emp_id)
	 * @GenericGenerator => generator for id of employee_offices table 
	 * @CollectionId => create primary key(id) for join table employee_offices
	 */
	@ElementCollection
	@JoinTable(name="employee_offices",joinColumns=@JoinColumn(name="emp_id"))
	@GenericGenerator(name="custom_id_gen",strategy="hilo")
	@CollectionId(columns=@Column(name="id_"),generator="custom_id_gen",type=@Type(type="long"))
	private Collection<Office> offices = new ArrayList<Office>();

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Collection<Office> getOffices() {
		return offices;
	}

	public void setOffices(Collection<Office> offices) {
		this.offices = offices;
	}
	
	
}
