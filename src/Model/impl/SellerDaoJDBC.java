package Model.impl;

import db.DB;
import Model.DAO.SellerDao;
import Model.Entities.Departament;
import Model.Entities.Seller;
import db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "Insert into seller (nome,email,birthDate,baseSalary,departamentId) values (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBasySalary());
            st.setInt(5, obj.getDepartament().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResult(rs);
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
    public void update(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE seller SET nome = ?, email = ?, birthDate = ?, baseSalary = ?, departamentId = ? WHERE Id = ?"
                    );
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBasySalary());
            st.setInt(5, obj.getDepartament().getId());
            st.setInt(6,obj.getId());
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
            st = conn.prepareStatement("Delete from seller where id = ?");
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
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "select seller .*, departament.nome as depName " + "from seller INNER JOIN departament ON "
                            + "seller.departamentId = departament.id " + "where seller.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Departament dep = instatiateDepartament(rs);
                Seller obj = instatiateSeller(rs, dep);
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
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "select seller .*, departament.nome as depName " + "from seller INNER JOIN departament ON "
                            + "seller.departamentId = departament.id " + "order by nome");
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Departament> map = new HashMap<>();
            while (rs.next()) {
                Departament dep = map.get(rs.getInt("DepartamentId"));
                if (dep == null) {
                    dep = instatiateDepartament(rs);
                    map.put(rs.getInt("DepartamentId"), dep);
                }
                Seller obj = instatiateSeller(rs, dep);
                list.add(obj);

            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStament(st);
            DB.closeResult(rs);
        }
    }

    private Seller instatiateSeller(ResultSet rs, Departament dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setBirthDate(rs.getDate("birthDate"));
        obj.setBasySalary(rs.getDouble("baseSalary"));
        obj.setDepartament(dep);
        return obj;
    }

    private Departament instatiateDepartament(ResultSet rs) throws SQLException {
        Departament dep = new Departament();
        dep.setId(rs.getInt("departamentId"));
        dep.setName(rs.getNString("depName"));
        return dep;
    }

    @Override
    public List<Seller> findByDepartmant(Departament department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "select seller .*, departament.nome as depName " + "from seller INNER JOIN departament ON "
                            + "seller.departamentId = departament.id " + "where DepartamentId = ? " + "order by nome");
            st.setInt(1, department.getId());
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Departament> map = new HashMap<>();
            while (rs.next()) {
                Departament dep = map.get(rs.getInt("DepartamentId"));
                if (dep == null) {
                    dep = instatiateDepartament(rs);
                    map.put(rs.getInt("DepartamentId"), dep);
                }
                Seller obj = instatiateSeller(rs, dep);
                list.add(obj);

            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStament(st);
            DB.closeResult(rs);
        }
    }

}
