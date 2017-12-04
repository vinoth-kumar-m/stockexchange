
package com.dtcc.stockexchange.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.dtcc.stockexchange.model.Stock;

/**
 * ExchangeMapper.java - This is mapper interface handle all database calls for exchange application
 * @author Vinoth
 *
 */
public interface ExchangeMapper {
	
	/**
	 * This method will retrieve the all stock details from database
	 * @return list of stocks holding by the user
	 */
	@Results(id = "stocks", value = {
			  @Result(property = "id", column = "id"),
			  @Result(property = "symbol", column = "symbol"),
			  @Result(property = "name", column = "name"),
			  @Result(property = "launchDate", column = "dol"),
			  @Result(property = "faceValue", column = "face_value"),
			  @Result(property = "totalShares", column = "total_shares"),
			  @Result(property = "availableShares", column = "available_shares"),
			  @Result(property = "marketPrice", column = "market_price")
			})
	@Select("SELECT * FROM stock WHERE status = 'ACTIVE'")
	List<Stock> getStocks();
	

	/**
	 * This method will refresh all the stock prices in the database
	 */
	@Select(value= "{ CALL  refresh_stock_price }")
	@Options(statementType = StatementType.CALLABLE)
	void refreshStockRates();
	
	
	/**
	 * This method will update stock selling details into database
	 * @param stock
	 */
	@Update("UPDATE stock SET available_shares=available_shares+#{quantity} WHERE symbol=#{symbol}")
	void sellStock(Stock stock);
	
	/**
	 * This method will retrieve the available shares of the given stock
	 * @param stock A variable of type Stock
	 * @return A variable of type int
	 */
	@Select("SELECT available_shares FROM stock WHERE symbol = #{symbol} AND status = 'ACTIVE'")
	Integer availableShares(String symbol);
	
	/**
	 * This method will insert stock buying details into database
	 * @param stock A variable of type Stock
	 */
	@Update("UPDATE stock SET available_shares=available_shares-#{quantity} WHERE symbol=#{symbol}")
	void buyStock(Stock stock);
	
	
	/**
	 * This method will retrieve the stock performance of the given stock from database
	 * @param symbol A variable of type String
	 * @return stock history
	 */
	@Results(id = "historicalData", value = {
			  @Result(property = "launchDate", column = "dol"),
			  @Result(property = "marketPrice", column = "price")
			})
	@Select("SELECT dol, price FROM (SELECT TRUNC(dol) dol, RANK() OVER (PARTITION BY TRUNC(dol) ORDER BY dol DESC) rn, price FROM stock_price WHERE symbol = #{symbol}) WHERE rn = 1")
	List<Stock> getStockPerformance(String symbol);
}
