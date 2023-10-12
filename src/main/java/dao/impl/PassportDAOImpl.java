package dao.impl;

import dao.PassportDAO;
import model.Passport;
import util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class PassportDAOImpl implements PassportDAO {
    @Override
    public boolean createPassport(Passport passport) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("INSERT INTO `passport` (`personal_id`, `ind_id`, `exp_ts`, `created_ts`) " +
                    "VALUES ('" + passport.getPersonalId() + "', '" + passport.getIndId() + "', '" + passport.getExpTs() + "', CURRENT_TIMESTAMP)");

            return count == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passport findById(int id) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM passport where id =" + id);
            if (rs.next()) {
                Passport passport = new Passport();
                passport.setId(id);
                passport.setPersonalId(rs.getString("personal_id"));
                passport.setIndId(rs.getString("ind_id"));
                passport.setExpTs(rs.getTimestamp("exp_ts"));
                passport.setCreatedTs(rs.getTimestamp("created_ts"));
                return passport;
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
            int count = statement.executeUpdate("DELETE FROM passport WHERE `passport`.`id` = " + id);
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePassport(Passport passport) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("UPDATE `passport` SET `personal_id` = '" + passport.getPersonalId() + "', " +
                    "`ind_id` = '" + passport.getIndId() + "', `exp_ts` = '" + passport.getExpTs() + "', `created_ts` = " + passport.getCreatedTs() + " WHERE `passport`.`id` = " + passport.getId());
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Passport> all() {
        Set<Passport> passports = new HashSet<>();
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM passport");
            while (rs.next()) {
                Passport passport = new Passport();
                passport.setId(rs.getInt("id"));
                passport.setPersonalId(rs.getString("personal_id"));
                passport.setIndId(rs.getString("ind_id"));
                passport.setExpTs(rs.getTimestamp("exp_ts"));
                passport.setCreatedTs(rs.getTimestamp("created_ts"));
                passports.add(passport);
            }
            return passports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdByIndId(String indId){
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id FROM `passport` WHERE `passport`.`ind_id` = '" + indId + "'");
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}