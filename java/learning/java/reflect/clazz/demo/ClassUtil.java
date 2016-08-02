package learning.java.reflect.clazz.demo;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

    /**
     * Print Class info, contains methods, fields, constructor
     * @param obj any object instance,not null
     */
    public static void printClassMessage(Object obj){
        //get class type, runtime class of this object
        Class<?> c = obj.getClass();

        //get the name of the class or interface
        System.out.println("Class full name:" + c.getName());

        System.out.println("---public methods---");
        printMethodsMessage(obj);

        System.out.println("---public fields---");
        printFieldsMessage(obj);

        System.out.println("---constructors---");
        printConstructorMessage(obj);

    }

    public static void printMethodsMessage(Object obj) {
        Class<?> c = obj.getClass();
        //get the public methods of this class
        Method[] methods = c.getMethods();
        //get all declare methods, but excluding inherited methods
        //Method[] declaredMethods = c.getDeclaredMethods();

        //print method name
        for (Method method : methods) {
            //get return type
            Class<?> returnType = method.getReturnType();
            System.out.print(returnType.getName() + " ");
            //method name
            System.out.print(method.getName() + "(");
            //parameter types
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ",");
            }
            System.out.println(")");

        }
    }

    public static void printFieldsMessage(Object obj) {
        Class<?> c = obj.getClass();
        //public fields
        Field[] fields = c.getFields();
        //all the declared fields of this class
        //Field[] declaredFields = c.getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            String typeName = type.getName();
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    public static void printConstructorMessage(Object obj){
        Class<?> c = obj.getClass();
        //Constructor<?>[] constructors = c.getConstructors();
        Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            //constructor name
            System.out.print(constructor.getName() + "(");
            //parameter types
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ",");
            }
            System.out.println(")");
        }

    }
}
