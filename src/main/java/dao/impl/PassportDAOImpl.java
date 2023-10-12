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
        return false;
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
                passport.setPersonalID(rs.getString("personal_id"));
                passport.setIndID(rs.getString("ind_id"));
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
        return false;
    }

    @Override
    public boolean updatePassport(Passport passport) {
        return false;
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
                passport.setPersonalID(rs.getString("personal_id"));
                passport.setIndID(rs.getString("ind_id"));
                passport.setExpTs(rs.getTimestamp("exp_ts"));
                passport.setCreatedTs(rs.getTimestamp("created_ts"));
                passports.add(passport);
            }
            return passports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}