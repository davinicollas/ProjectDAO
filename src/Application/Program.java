package Application;

import Model.DAO.DaoFactory;
import Model.DAO.SellerDao;
import Model.Entities.Departament;
import Model.Entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerdao = DaoFactory.createSellerDao();
        
        System.out.println("-------TEST 1: SELLER FINDBYID----------");
        Seller seller = sellerdao.findById(3);
        
        System.out.println(seller);
        
        
        System.out.println("-------\n TEST 2: SELLER findByDepartmant----------");
        Departament departament = new Departament(2, null);
        /*
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

        System.out.println("-------\n TEST 4: SELLER Insert----------");
		Seller newSeller = new Seller(null, "greg", "greg@greg", new Date(), 4000.00, departament);

        sellerdao.insert(newSeller);
        System.out.println("Insert " +newSeller.getId());

        System.out.println("-------\n TEST 5: SELLER UPDATE----------");
		seller = sellerdao.findById(1);
        seller.setName("MUsic");
        sellerdao.update(seller);
        System.out.println("update COmpleted! ");

        



        
        
    }
}
