import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import model.Product;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTests {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Product product1 = new Product();
        product1.setProductCode("123-123");
        product1.setProductLine("Classical Cars");
        product1.setBuyPrice(123.2222);

        Product product2 = new Product();

        Class<Product> productClass = Product.class;

        System.out.println(productClass == product1.getClass());
        System.out.println(productClass == product2.getClass());

        for (Field  field : productClass.getDeclaredFields()){
            System.out.println(field.getName());
        }
        System.out.println("---");
        for(Method method : productClass.getDeclaredMethods()){
            if (method.getName().startsWith("set")){
                System.out.println(method.getName());
            }
            if (method.getName().startsWith("get")) {
                System.out.println(method.invoke(product1, null));
            }
        }
        new ProductDAOImpl().findById("S10_4698");
    }

}
