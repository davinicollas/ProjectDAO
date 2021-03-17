package Application;

import Model.DAO.DaoFactory;
import Model.DAO.SellerDao;
import Model.Entities.Seller;

import java.util.Date;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerdao = DaoFactory.createSellerDao();
        
        System.out.println("-------TEST 1: SELLER FINDBYID----------");
        Seller seller = sellerdao.findById(3);
        
        System.out.println(seller);
        
        
    }
}
