package Model.DAO;

//import model.impl.DepartamentDaoJDBC;
import Model.impl.SellerDaoJDBC;
import db.DB;
public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }

}
