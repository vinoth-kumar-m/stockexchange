/**
 * 
 */
package com.dtcc.stockexchange.unittest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dtcc.stockexchange.dao.ExchangeMapper;
import com.dtcc.stockexchange.exception.ExchangeException;
import com.dtcc.stockexchange.model.Stock;
import com.dtcc.stockexchange.service.ExchangeService;
import com.dtcc.stockexchange.service.impl.ExchangeServiceImpl;

/**
 * @author Sri
 *
 */
public class ExchangeServiceTest {
	
	@InjectMocks
	private ExchangeService exchangeService;
	
	@Mock
	private ExchangeMapper exchangeMapper;
	
	private Stock stock;
	
	@Before
    public void setupMock() {
		stock = new Stock();
		stock.setId("2");
		stock.setSymbol("SBIN");
		stock.setQuantity(12);
		stock.setMarketPrice(2232.34);
		
		exchangeService = new ExchangeServiceImpl();
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testMockCreation(){
		assertNotNull(stock);
		assertNotNull(exchangeMapper);
		assertNotNull(exchangeService);
	}
	
	@Test(expected = ExchangeException.class)
	public void testStockQuanityAvailable() throws ExchangeException {
		System.out.println("--------------------------------------------------------------");
		System.out.println("Stubbing ExchangeMapper availableShares to return 10");
        when(exchangeMapper.availableShares(stock.getSymbol())).thenReturn(10);
       
        System.out.println("Calling exchangeMapper.buyStock()");
        try {
        	exchangeService.buyStock(stock);
        } catch (ExchangeException e) {
            System.out.println("ExchangeException has been thrown");
            verify(exchangeMapper, times(0)).buyStock(stock);
            
            System.out.println("Verified exchangeService.buyStock is not called");
            throw e;
        }
	}
	
	@Test
    public void testBuyStock() throws ExchangeException {
		System.out.println("--------------------------------------------------------------");
		System.out.println("Stubbing ExchangeMapper availableShares to return 100");
        when(exchangeMapper.availableShares(stock.getSymbol())).thenReturn(100);
        
        System.out.println("Stubbing ExchangeMapper buyStock");
        doNothing().when(exchangeMapper).buyStock(stock);

        exchangeService.buyStock(stock);
      
        System.out.println("Verified exchangeMapper.availableShares is called atleast once");
        verify(exchangeMapper, atLeastOnce()).availableShares(stock.getSymbol());

        System.out.println("Verified exchangeMapper.buyStock is called atleast once");
        verify(exchangeMapper, atLeastOnce()).buyStock(stock);
    }
}
