package com.aether.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.aether.model.character.Statistic;

public class TestStatistic {
	
	private Statistic statistic;

	@Before
	public void setUp() {
		statistic = new Statistic(10);
	}
	
	@Test
	public void test_Create_a_new_statistic_sets_max_and_value_to_same_value() throws Exception {
		assertEquals(10, statistic.getMax());
		assertEquals(10, statistic.getValue());
	}
	
	@Test
	public void test_Retrieve_value_as_percentage() throws Exception {
		statistic.setValue(8);
		assertEquals(8, statistic.getValue());
	}
}
