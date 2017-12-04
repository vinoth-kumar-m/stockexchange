/**
 * 
 */
package com.dtcc.stockexchange.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Stock.java - a simple modal class to hold information about a stock
 * @author Vinoth
 *
 */
public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String symbol;
	private String name;
	private Date launchDate;
	private Double faceValue;
	private Integer totalShares;
	private Integer availableShares;
	private Double marketPrice;
	private String  status;
	private Integer quantity;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the launchDate
	 */
	public Date getLaunchDate() {
		return launchDate;
	}
	/**
	 * @param launchDate the launchDate to set
	 */
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	/**
	 * @return the faceValue
	 */
	public Double getFaceValue() {
		return faceValue;
	}
	/**
	 * @param faceValue the faceValue to set
	 */
	public void setFaceValue(Double faceValue) {
		this.faceValue = faceValue;
	}
	/**
	 * @return the totalShares
	 */
	public Integer getTotalShares() {
		return totalShares;
	}
	/**
	 * @param totalShares the totalShares to set
	 */
	public void setTotalShares(Integer totalShares) {
		this.totalShares = totalShares;
	}
	/**
	 * @return the availableShares
	 */
	public Integer getAvailableShares() {
		return availableShares;
	}
	/**
	 * @param availableShares the availableShares to set
	 */
	public void setAvailableShares(Integer availableShares) {
		this.availableShares = availableShares;
	}
	/**
	 * @return the marketPrice
	 */
	public Double getMarketPrice() {
		return marketPrice;
	}
	/**
	 * @param marketPrice the marketPrice to set
	 */
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Stock) {
			return this.getSymbol().equals(((Stock) obj).getSymbol());
		}
		return false;
	}

}
