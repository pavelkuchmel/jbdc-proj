package dao.impl;

import dao.ProductDAO;
import model.Product;
import util.DBUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ProductDAOImpl implements ProductDAO {


    @Override
    public Set<Product> findAllByProductLine(String s) {
        return null;
    }

    @Override
    public boolean create(Product product) {
        return false;
    }

    @Override
    public Product findById(String key) {
        try(Connection connection = DBUtils.getConnection();){

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM classicmodels.products WHERE productCode = ?");
            preparedStatement.setString(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return create(new Product(), rs);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }

    @Override
    public boolean updateEmployee(Product product) {
        return false;
    }

    @Override
    public Set<Product> all() {
        return null;
    }

//    private Product createProduct(ResultSet rs) {
//        Product product = new Product();
//
//        try {
//            for (Field field : product.getClass().getDeclaredFields()){
//                String name = field.getName();
//                String type = field.getType().getSimpleName();
//
//                Object value = null;
//
//                switch (type) {
//                    case "String":
//                        value = rs.getString(name);
////                    try {
////                        field.set(this, value);
////                    } catch (IllegalAccessException e) {
////                        throw new RuntimeException(e);
////                    }
//                        break;
//                    case "Integer":
//                        value = rs.getInt(name);
//                        break;
//                    case "int":
//                        value = rs.getInt(name);
//                        break;
//                    case "Double":
//                        value = rs.getDouble(name);
//                        break;
//                    case "double":
//                        value = rs.getDouble(name);
//                        break;
//                    default:
//                        System.out.println("No implements for type " + type);
//                        break;
//                }
//
//                for (Method method : product.getClass().getDeclaredMethods()){
//                    if (method.getName().toLowerCase().startsWith("set" + name.toLowerCase())){
//                        method.invoke(this, value);
//                        break;
//                    }
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return product;
//    }
}
