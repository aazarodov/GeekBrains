package com.company.java3.homework7;

import com.company.java3.homework7.jtest.AfterSuite;
import com.company.java3.homework7.jtest.BeforeSuite;
import com.company.java3.homework7.jtest.JTest;
import com.company.java3.homework7.jtest.Test;

public class ClassTest {
    private String message;
    private String[] arrayOfPriority = new String[10];

    @BeforeSuite
    public void setMessage() {
        message = "This message is filled out before suite!";
        for (int i = 0; i < arrayOfPriority.length; i++) {
            arrayOfPriority[i] = "Priority " + (i + 1);
        }
    }

    @Test
    public void test1() {
        System.out.println(message);
    }

    @Test (priority = 1)
    public void testPriority1() {
        System.out.println(arrayOfPriority[0]);
    }

    @Test (priority = 5)
    public void testPriority5() {
        System.out.println(arrayOfPriority[4]);
    }

    @Test (priority = 7)
    public void testPriority7() {
        System.out.println(arrayOfPriority[6]);
    }

    @Test (priority = 10)
    public void testPriority10() {
        System.out.println(arrayOfPriority[9]);
    }

    @AfterSuite
    public void clearMessage() {
        message = "";
    }

//    Если раскоментировать один из нижних кусков кода, то получим RuntimeException

//    @BeforeSuite
//    public void setMessage1() {
//        message = "This message is filled out before suite!";
//    }

//
//    @Test (priority = 0)
//    public void testPriority0() {
//        System.out.println("Priority 0");
//    }

    public static void main(String[] args) {
        JTest.start(ClassTest.class);
    }
}
