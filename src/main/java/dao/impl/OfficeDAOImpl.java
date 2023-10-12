package dao.impl;

import dao.OfficeDAO;
import model.Office;
import model.Passport;
import util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("DELETE FROM offices WHERE `offices`.`id` = " + id);
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOffice(Office office) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("UPDATE `offices` SET `title` = '" + office.getTitle() + "', `address` = '" + office.getAddress() + "', `phone 1` = '" + office.getPhone1() + "', " +
                    "`phone 2` = '" + office.getPhone2() + "', `postal_code` = '" + office.getPostalCode() + "', `updated_ts` = 'CURRENT_TIMESTAMP', `created_ts` = '" + office.getCreatedTs() + "' WHERE `offices`.`id` = " + office.getId());
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Office> all() {
        Set<Office> offices = new HashSet<>();
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM offices");
            while (rs.next()) {
                Office office = new Office();
                office.setId(rs.getInt("id"));
                office.setTitle(rs.getString("title"));
                office.setAddress(rs.getString("address"));
                office.setPhone1(rs.getString("phone 1"));
                office.setPhone2(rs.getString("phone 2"));
                office.setPostalCode(rs.getInt("postal_code"));
                office.setUpdatedTs(rs.getTimestamp("updated_ts"));
                office.setCreatedTs(rs.getTimestamp("created_ts"));
                offices.add(office);
            }
            return offices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
