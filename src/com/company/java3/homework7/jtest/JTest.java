package com.company.java3.homework7.jtest;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class JTest {
    private static ArrayList<Method> beforeMethods;
    private static ArrayList<Method> afterMethods;
    private static ArrayList<ArrayList<Method>> testMethods;

    static {
        beforeMethods = new ArrayList<>();
        afterMethods = new ArrayList<>();
        testMethods = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            testMethods.add(i, new ArrayList<Method>());
        }
    }

    /**
     * Запуск теста
     * @param c Класс для теста (тип Class)
     */
    public static void start(Class c) {
        startTests(c);
    }

    /**
     * Запуск теста
     * @param className Класс для теста (тип String)
     */
    public static void start(String className) throws ClassNotFoundException {
        Class c = Class.forName(className);
        startTests(c);
    }

    /**
     * Очистка служебных массивов
     */
    private static void clearArrays() {
        beforeMethods.clear();
        afterMethods.clear();
        for (int i = 0; i < testMethods.size(); i++) {
            testMethods.get(i).clear();
        }
    }

    /**
     * Сборка методов для вызова при тесте
     * @param c
     */
    private static void prepare(Class c) {
        clearArrays();
        boolean setAccessible;
        // обходим методы класса
        for (Method declareMethod : c.getDeclaredMethods()) {
            setAccessible = false;
            // методы BeforeSuite
            if (declareMethod.getAnnotation(BeforeSuite.class) != null) {
                setAccessible = true;
                beforeMethods.add(declareMethod);
            }
            // методы AfterSuite
            if (declareMethod.getAnnotation(AfterSuite.class) != null) {
                setAccessible = true;
                afterMethods.add(declareMethod);
            }
            // методы Test
            Test testAnnotation = declareMethod.getAnnotation(Test.class);
            if (testAnnotation != null) {
                setAccessible = true;
                int priority = testAnnotation.priority();
                if (priority >= 1 && priority <= 10) {
                    testMethods.get(priority - 1).add(declareMethod);
                } else {
                    throw new RuntimeException();
                }
            }
            // если методы недоступен, откроем его
            if (setAccessible && !declareMethod.isAccessible()) {
                declareMethod.setAccessible(true);
            }
        }
    }

    /**
     * Выполнение самого теста
     * @param c Класс для теста (тип Class)
     */
    private static void startTests(Class c) {
        prepare(c);
        if (beforeMethods.size() > 1) {
            throw new RuntimeException();
        }
        if (afterMethods.size() > 1) {
            throw new RuntimeException();
        }
        try {
            Object o = c.newInstance();
            // методы BeforeSuite
            if (beforeMethods.size() == 1) {
                beforeMethods.get(0).invoke(o);
            }
            // методы Test
            for (ArrayList<Method> testMethod : testMethods) {
                for (Method method : testMethod) {
                    method.invoke(o);
                }
            }
            // методы AfterSuite
            if (afterMethods.size() == 1) {
                afterMethods.get(0).invoke(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
