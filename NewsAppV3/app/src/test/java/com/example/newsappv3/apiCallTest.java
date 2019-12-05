package com.example.newsappv3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// testing json string that requests for JSON objects, string has to be correct with proper key

public class apiCallTest {

    MainActivity tester;

    @Before
    public void setup(){
        tester = new MainActivity();
    }

    @Test
    //
    public void test1(){
        assertTrue(tester.onPostExecute("https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY ));
    }

    @Test
    // empty string to make API call
    public void test2() {
        assertFalse(tester.onPostExecute(" "));
    }

    @Test
    // false API key so string will not work
    public void test3() {
        assertFalse(tester.onPostExecute("https://newsapi.org/v2/top-headlines?country=us&apiKey=falseAPIKEY"));
    }
}
