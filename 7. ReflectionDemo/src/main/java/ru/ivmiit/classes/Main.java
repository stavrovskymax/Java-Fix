package ru.ivmiit.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String className = scanner.next();
        Class aClass = Class.forName(className);

        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " " + field.getName());
        }

        /*Object object = aClass.newInstance();
        System.out.println(object);*/

        Class[] types = new Class[fields.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = fields[i].getType();
        }
        Constructor constructor = aClass.getDeclaredConstructor(types);
        for (Class parameterType : constructor.getParameterTypes()) {
            System.out.print(parameterType.getName() + " ");
        }

        Integer intValue = 0;
        String stringValue = "";
        for (int i = 0; i < types.length; i++) {
            if (types[i].getName().equals("int")) {
                intValue = scanner.nextInt();
            } else if (types[i].getName().equals("java.lang.String")) {
                stringValue = scanner.next();
            }
        }
        Object[] arguments = {intValue, stringValue};
        Object object = constructor.newInstance(arguments);
        System.out.println(object);
    }
}
