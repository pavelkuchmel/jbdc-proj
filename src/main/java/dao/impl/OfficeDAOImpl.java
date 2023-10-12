package dao.impl;

import dao.OfficeDAO;
import model.Office;
import util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class OfficeDAOImpl implements OfficeDAO {

    @Override
    public boolean createOffice(Office office) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("INSERT INTO offices (title, address,  `phone 1`, `phone 2`, postal_code, created_ts) "
                    +
                    "VALUES ('"+office.getTitle()+"', '"+office.getAddress()
                    +"', '"+office.getPhone1()+"', '"+office.getPhone2()+"', '"
                    +office.getPostalCode()+"', CURRENT_TIMESTAMP)");

            return count == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Office findById(int id) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM offices where id =" + id);
            if (rs.next()) {
                Office office = new Office();
                office.setId(id);
                office.setTitle(rs.getString("title"));
                office.setAddress(rs.getString("address"));
                office.setPhone1(rs.getString("phone 1"));
                office.setPhone2(rs.getString("phone 2"));
                office.setPostalCode(rs.getInt("postal_code"));
                office.setCreatedTs(rs.getTimestamp("created_ts"));
                office.setUpdatedTs(rs.getTimestamp("updated_ts"));
                return office;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean updateOffice(Office office) {
        return false;
    }

    @Override
    public Set<Office> all() {
        return null;
    }
}
