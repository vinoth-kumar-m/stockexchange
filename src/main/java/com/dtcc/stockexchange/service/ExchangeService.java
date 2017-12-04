
package com.dtcc.stockexchange.service;

import java.util.List;

import com.dtcc.stockexchange.exception.ExchangeException;
import com.dtcc.stockexchange.model.Stock;


/**
 * ExchangeService.java - this is a service class handles all the webservice calls and process it
 * @author Vinoth
 *
 */
public interface ExchangeService {
	
	/**
	 * This method helps to refresh price of all the stocks in stock exchange and return the stocks with update price
	 * @return list of all the stocks from stock exchange
	 * @throws ExchangeException
	 */
	List<Stock> refreshStockRates() throws ExchangeException;

	/**
	 * This method helps to retrieve all the stocks in stock exchange
	 * @return list of all the stocks
	 * @throws ExchangeException
	 */
	List<Stock> getStocks() throws ExchangeException;
	
	/**
	 * This method helps to buy stock from stock exchange
	 * @param stock A variable of type Stock
	 * @throws ExchangeException
	 */
	void buyStock(Stock stock) throws ExchangeException;

	/**
	 * This method helps to sell stock to stock exchangee
	 * @param stock A variable of type Stock
	 * @throws ExchangeException
	 */
	void sellStock(Stock stock) throws ExchangeException;
	
	/**
	 * This method helps to retrieve stock performance of the given stock from stock exchange
	 * @return history of given stock
	 * @throws ExchangeException
	 */
	List<Stock> getStockPerformance(String symbol) throws ExchangeException;
}
