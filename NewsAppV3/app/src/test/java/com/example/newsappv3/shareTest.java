package com.example.newsappv3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class shareTest {

    DisplayFullArticle tester;

    @Before
    public void setup(){ tester = new DisplayFullArticle();}

    @Test
    //test for wrong data
    public void test1(){assertTrue(tester.urlExists("Wrong data"));}

    @Test
    //test url from webview
    public void test2(){assertTrue(tester.urlExists(tester.url));}

    @Test
    //test valid url not from webview
    public void test3(){assertTrue(tester.urlExists(("https://www.google.com/search?q=puppies&sxsrf=ACYBGNTcogHlPXt9mcI2HNtR2i7Ejluhyw:1575510645937&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjf6Y_Dsp3mAhVCQq0KHeZJCbMQ_AUoAXoECBUQAw&biw=1920&bih=979")));}
}
