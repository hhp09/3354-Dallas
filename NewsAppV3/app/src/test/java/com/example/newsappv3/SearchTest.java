package com.example.newsappv3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchTest {

    Search tester;

    @Before
    public void setup(){ tester = new Search();}

    @Test
    // Test a single word
    public void test1() {
        assertTrue( tester.isValidInput("Tesla"));
    }

    @Test
    // Test text with spaces in between
    public void test2() {
        assertTrue( tester.isValidInput("Small house"));
    }

    @Test
    // Test a single character
    public void test3() {
        assertTrue( tester.isValidInput("x"));
    }

    @Test
    // Test with spaces at the beginning and end
    public void test4() {
        assertTrue( tester.isValidInput("   tesla   "));
    }

    @Test
    // Test with numbers
    public void test5() {
        assertTrue( tester.isValidInput("1998"));
    }

    @Test
    // Test with only spaces
    public void test6() {
        assertFalse( tester.isValidInput("     "));
    }

    @Test
    // Test with empty input
    public void test7() {
        assertFalse( tester.isValidInput(""));
    }
}