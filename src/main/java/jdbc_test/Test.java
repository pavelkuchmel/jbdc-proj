package jdbc_test;

import dao.EmployeeDAO;
import dao.OfficeDAO;
import dao.PassportDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.OfficeDAOImpl;
import dao.impl.PassportDAOImpl;
import model.Employee;
import model.Office;
import model.Passport;

import java.sql.Time;
import java.sql.Timestamp;

public class Test {

    public static void main(String[] args) {
/*
        Employee empl1 = new EmployeeDAOImpl().findById(2);
        System.out.println(empl1.getOffice());
*/
//        System.out.println(new EmployeeDAOImpl().all().size());
//        Office office = new Office();
//        office.setAddress("adtress");
//        office.setPhone1("23454");
//        office.setPhone2("3242424");
//        office.setPostalCode(2222222);
//        office.setTitle("MAIN 2");

        //boolean isCreated = new OfficeDAOImpl().createOffice(office);

        //System.out.println(new OfficeDAOImpl().createOffice(office));

        Passport passport1 = new Passport();
        passport1.setIndId("ID9999999");
        passport1.setPersonalId("MP999999");
        passport1.setExpTs(Timestamp.valueOf("2024-07-22 09:08:07"));

        Employee employee1 = new Employee();
        employee1.setName("Robert");
        employee1.setLastName("Downey");
        employee1.setAge(58);
        employee1.setOffice(new OfficeDAOImpl().findById(1));
        employee1.setPassport(passport1);
        employee1.setCreatedTs(new Timestamp(System.currentTimeMillis()));

        EmployeeDAO  employeeDAO = new EmployeeDAOImpl();
        employeeDAO.createEmployee(employee1);

    }
}
