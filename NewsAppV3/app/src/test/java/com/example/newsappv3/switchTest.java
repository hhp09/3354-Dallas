package com.example.newsappv3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class switchTest 
{
	Switch tester;
	
	@Before
	public void setup()
	{
		tester = new Switch();
	}
	
	@Test
	//Test valid activity
	public void testOne()
	{
		assertTrue(tester.isValidActivity("MainActivity"));
	}
	
	@Test
	//Test valid activity
	public void testTwo()
	{
		assertTrue(tester.isValidActivity("Search"));
	}
	
	@Test
	//Test valid activity
	public void testThree()
	{
		assertTrue(tester.isValidActivity("Save"));
	}
	
	@Test
	//Test invalid activity
	public void testFour()
	{
		assertFalse(tester.isValidActivity("Weather"));
	}
	
	@Test
	//Test invalid activity
	public void testFive()
	{
		assertFalse(tester.isValidActivity("Credits"));
	}

}
