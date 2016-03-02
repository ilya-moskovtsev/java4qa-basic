package com.db;

import com.db.dal.TickerDAO;
import com.db.domain.StockDAO;
import com.db.domain.Ticker;
import com.db.service.BuyOperationException;
import com.db.service.Controller;
import com.db.service.SellOperationException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ControllerSystemTest {
    private Controller controller = new Controller(new TickerDAO(), new StockDAO());

    @Before
    public void setUp() {

    }

    @Test
    public void shouldDoBuyForOneLotOfExistTicker() throws SQLException, BuyOperationException {
        assertEquals(1.0, controller.buy("TKRGZP", 1), 0.01);
    }

    @Test(expected = java.lang.ArithmeticException.class)
    public void shouldGetErrorWhenSellZeroLot() throws SQLException, BuyOperationException, SellOperationException {
        controller.sell("TKR", 0);
    }

    @Test (expected = BuyOperationException.class)
    public void shouldThrowBuyException () throws BuyOperationException,SQLException {
        controller.buy("TEST", -1);
    }

    @Test (expected = SellOperationException.class)
    public void shouldThrowSellException () throws SellOperationException,SQLException {
        controller.sell("TEST", -1);
    }
//    @Test
//    public void shouldSellAmount () throws SellOperationException,SQLException {
//        Controller controller = new Controller(new TickerDAO(), new StockDAO());
//        controller.sell("TEST", 10);
//    }

    @Test
    public void shouldGetTicker () throws SellOperationException,SQLException {
        Ticker ticker = new Ticker("TEST");
        assert "TEST" == ticker.getTickerId();
    }

    @Test
    public void shouldGetTickerNotnull () throws SellOperationException,SQLException {
        Ticker ticker = new Ticker("TEST");
        assertNotNull(ticker.getTickerId());
    }

    @Test
    public void shouldGetTickernull () throws SellOperationException,SQLException {
        Ticker ticker = new Ticker(null);
        assertNull(ticker.getTickerId());
    }

    @Test (expected = SQLException.class)
    public void shouldReceiveExceptionWhenFalse () throws SQLException {
        TickerDAO tickerDAO = new TickerDAO();
        tickerDAO.openConnection("test");
    }

    @Test
    public void shouldReceiveExceptionWhenToString () {
        TickerDAO tickerDAO = new TickerDAO();
        try {
            tickerDAO.openConnection("test");
        }
        catch (SQLException e){
            assertEquals("Wrong connection string: " + "test", e.getMessage());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldReturnIllegalArg () throws IllegalArgumentException {
        StockDAO stock = new StockDAO();
        stock.placeOrder("TEST", 1, -1);
    }

    @Test
    public void shouldReturnIllegalArgWhenFalse () {
        StockDAO stock = new StockDAO();
        try {
            stock.placeOrder("TEST", -1, -1);
        }
        catch (IllegalArgumentException e){
            assertEquals("Below zero amount", e.getMessage());
        }
    }

    @Test
         public void shouldReturnIllegalArgWhenFalse1 () {
        StockDAO stock = new StockDAO();
        try {
            stock.placeOrder("TEST", 1, -1);
        }
        catch (IllegalArgumentException e){
            assertEquals("Unsupported operation " + "-1", e.getMessage());
        }
    }

    @Test
    public void shouldReturnIllegalArgWhenFalse2 () {
        StockDAO stock = new StockDAO();
        double i = 0.;

            i = stock.placeOrder("TEST", 1, 1);

            assertEquals(1., i, 0.01);

    }

    @Test
    public void shouldReturnBuyWhen1 () {
        StockDAO stock = new StockDAO();
        double i = 0.;

        i = stock.placeOrder("TEST", 10, 1);

        assertEquals(.1, i, 0.01);

    }

    @Test
    public void shouldReturnSellWhen1 () {
        StockDAO stock = new StockDAO();
        double i = 0.;

        i = stock.placeOrder("TEST", 1, 2);

        assertEquals(1, i, 0.01);

    }
}
