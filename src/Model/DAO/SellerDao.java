package Model.DAO;

import Model.Entities.Departament;
import Model.Entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void delete(Seller obj);
    Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartmant(Departament dep);
}
