package model.DAO;

import model.entities.Departament;

import java.util.List;

public interface DepartamentDao {

    void insert(Departament obj);
    void update(Departament obj);
    void delete(Departament obj);
    Departament findById(Integer id);
    List<Departament> findAll();


    
}
