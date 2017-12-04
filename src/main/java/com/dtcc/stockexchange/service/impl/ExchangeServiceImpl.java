/**
 * 
 */
package com.dtcc.stockexchange.service.impl;

import java.util.List;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtcc.stockexchange.dao.ExchangeMapper;
import com.dtcc.stockexchange.exception.ExchangeException;
import com.dtcc.stockexchange.model.Stock;
import com.dtcc.stockexchange.service.ExchangeService;


/**
 * ExchangeServiceImpl.class - This class provides implementation for ExchangeService
 * @author Vinoth
 *
 */
@Service("exchangeService")
public class ExchangeServiceImpl implements ExchangeService {
	
	@Autowired
	private ExchangeMapper exchangeMapper;

	/* (non-Javadoc)
	 * @see com.dtcc.stockbroker.service.StockExchangeService#refreshStockRates()
	 */
	@Override
	public List<Stock> refreshStockRates() throws ExchangeException {
		try {
			exchangeMapper.refreshStockRates();
			return exchangeMapper.getStocks();
		} catch(MyBatisSystemException ex) {
			throw new ExchangeException(ex.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.dtcc.stockbroker.service.StockExchangeService#getStocks()
	 */
	@Override
	public List<Stock> getStocks() throws ExchangeException {
		try {
			return exchangeMapper.getStocks();
		} catch(MyBatisSystemException ex) {
			throw new ExchangeException(ex.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.dtcc.stockbroker.service.StockExchangeService#sellStock(com.dtcc.stockbroker.model.Stock)
	 */
	@Override
	public void sellStock(Stock stock) throws ExchangeException {
		try {
			exchangeMapper.sellStock(stock);
		} catch(MyBatisSystemException ex) {
			throw new ExchangeException(ex.getMessage());
		}
	}


	/* (non-Javadoc)
	 * @see com.dtcc.stockbroker.service.StockExchangeService#buyStock(com.dtcc.stockbroker.model.Stock)
	 */
	@Override
	public void buyStock(Stock stock) throws ExchangeException {
		Integer availableShares = 0;
		try {
			availableShares = exchangeMapper.availableShares(stock.getSymbol());
			if(stock.getQuantity() > availableShares) {
				throw new ExchangeException("Required quantity not available");
			}
			exchangeMapper.buyStock(stock);
		} catch(MyBatisSystemException ex) {
			throw new ExchangeException(ex.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.dtcc.stockbroker.service.StockExchangeService#getStockPerformance(java.lang.String)
	 */
	@Override
	public List<Stock> getStockPerformance(String symbol) throws ExchangeException {
		try {
			return exchangeMapper.getStockPerformance(symbol);
		} catch(MyBatisSystemException ex) {
			throw new ExchangeException(ex.getMessage());
		}
	}
}
