package Model.DAO;

import Model.impl.DepartamentDaoJDBC;
import Model.impl.SellerDaoJDBC;
import db.DB;
public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartamentDao createDepartamentoDao(){
        return new DepartamentDaoJDBC(DB.getConnection());
    }

}
