package dao.abs;

import model.Employee;
import model.Office;
import model.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Set;

public interface AbstractDAO<T, K> {

    boolean create(T type);
    T findById(K key);
    boolean deleteById(K key);
    boolean updateEmployee(T type);
    Set<T> all();

    default T create(T t, ResultSet rs) {
        try {
            for (Field field : t.getClass().getDeclaredFields()){
                String name = field.getName();
                String type = field.getType().getSimpleName();

                Object value = null;

                switch (type) {
                    case "String":
                        value = rs.getString(name);
//                    try {
//                        field.set(this, value);
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    }
                        break;
                    case "Integer":
                        value = rs.getInt(name);
                        break;
                    case "int":
                        value = rs.getInt(name);
                        break;
                    case "Double":
                        value = rs.getDouble(name);
                        break;
                    case "double":
                        value = rs.getDouble(name);
                        break;
                    default:
                        System.out.println("No implements for type " + type);
                        break;
                }

                for (Method method : t.getClass().getDeclaredMethods()){
                    if (method.getName().toLowerCase().startsWith("set" + name.toLowerCase())){
                        method.invoke(t, value);
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return t;
    }

}
