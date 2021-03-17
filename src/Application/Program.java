package Application;

import Model.DAO.DaoFactory;
import Model.DAO.SellerDao;
import Model.Entities.Departament;
import Model.Entities.Seller;

import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerdao = DaoFactory.createSellerDao();
        
        System.out.println("-------TEST 1: SELLER FINDBYID----------");
        Seller seller = sellerdao.findById(3);
        
        System.out.println(seller);
        
        
        /*System.out.println("-------\n TEST 2: SELLER findByDepartmant----------");
        Departament departament = new Departament(2, null);
		List<Seller> list = sellerdao.findByDepartmant(departament);
        for(Seller obj : list) {
        	System.out.println(obj);	
        	
        }
*/
        System.out.println("-------\n TEST 3: SELLER findall----------");
		List<Seller> list = sellerdao.findAll();
        for(Seller obj : list) {
        	System.out.println(obj);	
        	
        }
        
        
    }
}
