package Model.DAO;

import  Model.Entities.Departament;

import java.util.List;

public interface DepartamentDao {

    void insert(Departament obj);
    void update(Departament obj);
    void delete(Integer id);
    Departament findById(Integer id);
    List<Departament> findAll();


    
}
