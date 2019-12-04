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
	//Test invalid activity
	public void testTwo()
	{
		assertFalse(tester.isValidActivity("Weather"));
	}

}
