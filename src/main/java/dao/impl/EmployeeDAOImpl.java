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
        return false;
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
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
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
        return null;
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
