package dao;

import dao.abs.AbstractDAO;
import model.Product;

import java.util.Set;

public interface ProductDAO extends AbstractDAO<Product, String> {

    Set<Product> findAllByProductLine(String productLine);


}
