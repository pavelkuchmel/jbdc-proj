package dao.impl;

import dao.EmployeeDAO;
import dao.OfficeDAO;
import dao.PassportDAO;
import model.Employee;
import model.Office;
import util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDAOImpl implements EmployeeDAO {

    private OfficeDAO officeDAO = new OfficeDAOImpl();

    private PassportDAO passportDAO = new PassportDAOImpl();

    @Override
    public boolean createEmployee(Employee employee) {
        passportDAO.createPassport(employee.getPassport());
        //officeDAO.createOffice(employee.getOffice());
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("INSERT INTO `employees` (`name`, `last_name`, `age`, `office_id`, `passport_id`, `created_ts`) " +
                    "VALUES ('" + employee.getName() + "', '" + employee.getLastName() + "', '" + employee.getAge() + "', '" + employee.getOffice().getId() + "', '" + passportDAO.getIdByIndId(employee.getPassport().getIndId()) + "', CURRENT_TIMESTAMP)");

            return count == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findById(int id) {
        try(Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees where id =" + id);) {
            if(rs.next()){
                System.out.println("Employees with ID = " + id + " found!");
                return createEmployee(rs);
                /*Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setOffice(officeDAO.findById(rs.getInt("office_id")));
                employee.setAge(rs.getInt("age"));
                employee.setPassport(passportDAO.findById(rs.getInt("passport_id")));
                employee.setCreatedTs(rs.getTimestamp("created_ts"));
                employee.setUpdatedTs(rs.getTimestamp("updated_ts"));
                return employee;*/
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
            int count = statement.executeUpdate("DELETE FROM employees WHERE `employees`.`id` = " + id);
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate("UPDATE `employees` SET `name` = '" + employee.getName() + "', `age` = '" + employee.getAge() + "', `passport_id` = '" + employee.getPassport().getId() + "'," +
                    " `updated_ts` = 'CURRENT_TIMESTAMP', `created_ts` = '" + employee.getCreatedTs() + "' WHERE `employees`.`id` = " + employee.getId());
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Employee> all() {

        Set<Employee> all = new HashSet<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()){
                all.add(createEmployee(rs));
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            DBUtils.release(conn, stmt, rs);
        }

        return all;
    }

    @Override
    public Set<Employee> getAllByOfficeId(Office office) {
        Set<Employee> all = new HashSet<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `users_db`.`offices` WHERE `id` = " + office.getId());
            while (rs.next()){
                all.add(createEmployee(rs));
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            DBUtils.release(conn, stmt, rs);
        }
        return all;
    }

    private Employee createEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setOffice(officeDAO.findById(rs.getInt("office_id")));
        employee.setAge(rs.getInt("age"));
        employee.setPassport(passportDAO.findById(rs.getInt("passport_id")));
        employee.setCreatedTs(rs.getTimestamp("created_ts"));
        employee.setUpdatedTs(rs.getTimestamp("updated_ts"));
        return employee;
    }
}
