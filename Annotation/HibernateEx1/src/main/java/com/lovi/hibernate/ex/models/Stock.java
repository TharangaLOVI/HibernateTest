package com.lovi.hibernate.ex.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * In this Entity name is not changed
 * The table name is changed.So Entity name is keep as class name
 * @author Tharanga
 *
 */
@Entity
//@Entity(name="stock_table")
@Table(name="stock_table")
public class Stock{
 
	@Id 
	/**
	 * generated value for primary(depend on the data type),
	 * GenerationType.AUTO -> hibernate get decision according to used database
	 * 
	 */
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="stock_id")
	private int stockId;
	
	@Column(name="stock_code")
	private String stockCode;
	
	@Column(name="stock_name")
	private String stockName;
	
	/**
	 * skip nic to db
	 */
	@Transient
	private String nic;
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	
	
	public String getStockCode() {
		return stockCode;
	}
	
	
	public void setStockCode(String stockCode) {
		this.stockCode = "I changed " +  stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
 
	
}