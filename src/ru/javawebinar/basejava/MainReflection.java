package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class classObj = Resume.class;
        Field field = classObj.getDeclaredFields()[0];
        System.out.println(field.getName());

        Object obj = classObj.newInstance();
        field.setAccessible(true);
        field.set(obj, "new_uuid_2");

        Method m = classObj.getMethod("toString");
        System.out.println(m.invoke(obj));
    }
}
