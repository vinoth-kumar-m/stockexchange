
package com.dtcc.stockexchange.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtcc.stockexchange.exception.ExchangeException;
import com.dtcc.stockexchange.model.Stock;
import com.dtcc.stockexchange.service.ExchangeService;

/**
 * ExchangeController.java - this is a controller class which handles all rest requests and process it
 * @author Vinoth
 *
 */

@RestController
public class ExchangeController {

	@Autowired
	private ExchangeService exchangeService;

	/**
	 * This method will help to refresh stock details
	 * @param request A variable of type HttpServletRequest
	 * @return list of all stocks
	 * @throws ExchangeException
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET, produces = "application/json")
	public List<Stock> refreshStockRates(HttpServletRequest request) throws ExchangeException {
		List<Stock> stocks = exchangeService.refreshStockRates();
		return stocks;
	}

	/**
	 * This method will help to retrieve all the stock details
	 * @param request A variable of type HttpServletRequest
	 * @return list of all stocks
	 * @throws ExchangeException
	 */
	@RequestMapping(value = "/stocks", method = RequestMethod.GET, produces = "application/json")
	public List<Stock> getStocks(HttpServletRequest request) throws ExchangeException {
		List<Stock> stocks = exchangeService.getStocks();
		return stocks;
	}

	/**
	 * This method will help to retrieve the stock performance of the given stock
	 * @param symbol A variable of type String
	 * @param request A variable of type HttpServletRequest
	 * @return stock performance
	 * @throws ExchangeException
	 */
	@RequestMapping(value = "/performance/{symbol}", method = RequestMethod.GET, produces = "application/json")
	public List<Stock> getStockPerformance(@PathVariable("symbol") String symbol, HttpServletRequest request)
			throws ExchangeException {
		List<Stock> history = exchangeService.getStockPerformance(symbol);
		return history;
	}
	
	/**
	 * This method will help to sell the stock into exchange
	 * @param stock A variable of type Stock
	 * @param request A variable of type HttpServletRequest
	 * @return string
	 * @throws ExchangeException
	 */
	@RequestMapping(value = "/sell", method = RequestMethod.POST)
	public String sellStock(@RequestBody Stock stock, HttpServletRequest request)
			throws ExchangeException {
		exchangeService.sellStock(stock);
		return "Success";
	}

	/**
	 * This method will help to buy the stock from exchange
	 * @param stock A variable of type Stock
	 * @param request A variable of type HttpServletRequest
	 * @return string
	 * @throws ExchangeException
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buyStock(@RequestBody Stock stock, HttpServletRequest request)
			throws ExchangeException {
		exchangeService.buyStock(stock);
		return "Success";
	}

}
