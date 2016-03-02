//package com.db;
//
//import com.db.dal.TickerDAO;
//import com.db.domain.StockDAO;
//import com.db.service.BuyOperationException;
//import com.db.service.Controller;
//
//import java.sql.SQLException;
//
//public class ClientUI {
//    public static void main(String[] args ) throws BuyOperationException, SQLException {
//        Controller controller = new Controller(new TickerDAO(), new StockDAO());
//        controller.buy("GZP", 10);
//    }
//}
