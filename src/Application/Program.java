package Application;

import Model.DAO.DaoFactory;
import Model.DAO.DepartamentDao;
import Model.DAO.SellerDao;
import Model.Entities.Departament;
import Model.Entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Mostrar via id: ");
        DepartamentDao deppartamentDao = DaoFactory.createDepartamentoDao();
        Departament dep = deppartamentDao.findById(3);
        System.out.println(dep);

        System.out.println("Mostrar todos: ");

        List<Departament>list = deppartamentDao.findAll();

        for(Departament obj: list){
            System.out.println(obj);
        }

        System.out.println("Inserir: ");

        Departament NewDepar = new  Departament(null,"caixa");
        deppartamentDao.insert(NewDepar);
        System.out.println("Insert " + NewDepar.getId());


        System.out.println("deletar: ");
        System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		deppartamentDao.delete(id);
		System.out.println("Delete completed");

		System.out.println("update:: ");
        Departament dep2 = deppartamentDao.findById(1);
		dep2.setName("Food");
		deppartamentDao.update(dep2);
		System.out.println("Update completed");

        /*SellerDao sellerdao = DaoFactory.createSellerDao();
        
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

        
        System.out.println("-------\n TEST 6: SELLER Delete----------");
        System.out.println("Informe o ID de delete");
        int ids = sc.nextInt();
        sellerdao.delete(ids);
        System.out.println("Excluidp! ");
        sc.close();


        */


       /* Departament NewDepar = new  Departament(null,"caixa");
        dep.insert(NewDepar);
        System.out.println("Insert " + NewDepar.getId());
*/

    
    }
}
