package ru.ivmiit.demo;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        SomeClass someObject = new SomeClass();
        Class<SomeClass> someClassASClass = (Class<SomeClass>) someObject.getClass();
        Field someFieldAsObject = someClassASClass.getField("someField");
        System.out.println(someFieldAsObject.getType());

        Field[] fields = someClassASClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " " + field.getName());
        }

        System.out.println(someObject.someField);
        someFieldAsObject.set(someObject, 777);
        System.out.println(someObject.someField);

        Field privateField = someClassASClass.getDeclaredField("somePrivate");
        System.out.println(privateField.getName());

        privateField.setAccessible(true);
        privateField.set(someObject, "Max");
        System.out.println(someObject.getSomePrivate());
    }
}
