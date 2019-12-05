package com.example.newsappv3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/* Abby's Test Cases */
public class ArticleTest {
    Article tester;
    @Before
    public void setup(){
        tester = new Article();
    }

    @Test //tests .getAll is empty and return url
    public void test1(){
        assertEquals("Testing .getAll","\t\t\t\n",tester.getAll());
    }

    @Test //Tests populating Article
    public void test2(){
        tester.setInfo("A","U","D","C");
        assertEquals("\n Testing if .getAll retrieves all information","A\tU\tD\tC",tester.getAll());
    }

    @Test //Tests if a field is empty
    public void test3(){
        tester.setInfo("A","U","D","");
        assertEquals("\n Testing if .getAll retrieves all information","A\tU\tD\t",tester.getAll());
    }
}
