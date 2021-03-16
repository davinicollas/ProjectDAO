package model.impl;

import model.DAO.SellerDao;
import model.entities.Departament;
import model.entities.Seller;
import db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void delete(Seller obj) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "select seller .*, departament.name as depName "
                            +"from seller INNER JOIN departament ON "
                            +"seller.departamentId = departament.id "
                            +"where seller.id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()) {
                Departament dep = new Departament();
                dep.setId(rs.getInt("departamentId"));
                dep.setName(rs.getNString("depName"));
                Seller obj = new Seller();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setBirthDate(rs.getDate("birthDate"));
                obj.setBasySalary(rs.getDouble("baseSalary"));
                obj.setDepartament(dep);
                return obj;


            }
                return null;

        }catch (SQLException e){
           throw new DbException("Error: " +e.getMessage());
        }finally {
            DB.closeStament(st);
            DB.closeResult(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
