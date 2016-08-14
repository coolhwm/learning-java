package learning.java.annotation.orm;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Filter {

    public String query(){
        String tableName = getTableName();
        Map<String, Object> columns = getColumns();
        String sql = "SELECT * FROM " + tableName + " WHERE 1=1 ";
        Iterator<Map.Entry<String, Object>> it = columns.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String columnName = entry.getKey();
            Object columnValue = entry.getValue();
            sql += " AND " + columnName + " = '" + columnValue.toString() + "' ";
        }
        return sql;
    }

    private Map<String,Object> getColumns(){
        Class<? extends Filter> c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        Map<String, Object> columnMap = new HashMap<>();
        for (Field field : fields) {
            boolean isColumnField = field.isAnnotationPresent(Column.class);
            if (isColumnField) {
                Column annotation = field.getAnnotation(Column.class);
                String columnName = annotation.value();
                Object columnValue = getFiledValueDefaultNulls(field);
                if(columnValue != null){
                    columnMap.put(columnName,columnValue);
                }
            }
        }
        return columnMap;
    }


    private Method getFieldGetterMethod(Field field){
        String fieldName = field.getName();
        String methodName = "get" + toStartWithUpperCase(fieldName);
        Class<? extends Filter> c = this.getClass();
        try {
            return c.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("字段不存在getter方法[" +methodName+ "]:" + fieldName);
        }
    }

    private Object getFiledValueDefaultNulls(Field field){
        Method getter = getFieldGetterMethod(field);
        try {
            return getter.invoke(this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(field.getName() + "字段值获取失败");
        }
    }

    private String toStartWithUpperCase(String fieldName){
        String firstChar = fieldName.charAt(0) + "";
        return firstChar.toUpperCase() + fieldName.substring(1,fieldName.length());
    }


    private String getTableName(){
        Class<? extends Filter> c = this.getClass();
        boolean isExists = c.isAnnotationPresent(Table.class);
        if (isExists) {
            Table isTableClass = c.getAnnotation(Table.class);
            return isTableClass.value();
        }
        else{
            throw new IllegalArgumentException("不支持该类，没有被@Table修饰");
        }
    }
}
