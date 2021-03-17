package Model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.DAO.DepartamentDao;
import Model.Entities.Departament;
import db.DB;
import db.DbException;

public class DepartamentDaoJDBC  implements DepartamentDao{
    private Connection conn;

    public DepartamentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Departament obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "Insert into departament (nome) values (?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Nenhuma linha foi afetada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStament(st);

        }
    }

    @Override
    public void update(Departament obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE departament SET nome = ? WHERE Id = ?"
                    );
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStament(st);
        }
        
    }

    @Override
    public void delete(Integer id) {
        
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("Delete from departament where id = ?");
            st.setInt(1,id);

            int rows = st.executeUpdate();
            if(rows == 0){
                throw new DbException("Não ha id existente");
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStament(st);
        }
    }

    @Override
    public Departament findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "select * from departament where id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Departament obj =  new Departament();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("nome"));
                return obj;

            }
            
            return null;

        } catch (SQLException e) {
            throw new DbException("Error: " + e.getMessage());
        } finally {
            DB.closeStament(st);
            DB.closeResult(rs);
        }
    }


    @Override
    public List<Departament> findAll() {
        PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM departament ORDER BY nome");
			rs = st.executeQuery();

			List<Departament> list = new ArrayList<>();

			while (rs.next()) {
				Departament obj = new Departament();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("nome"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStament(st);
			DB.closeResult(rs);
		}
	
    }


  

}
