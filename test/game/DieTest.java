package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the class die and its associated methods
 * 
 * @authors Chris, Ryan
 */
public class DieTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tests that default constructor works
	 */
	@Test
	public void testDefaultConstructor() {
		Die die = new Die();
		assertNotNull(die);
	}

	/**
	 * Tests the method rollDie finds a result within the parameters of its sides
	 */
	@Test
	public void testRollDie() {
		Die die = new Die();
		int sides = Die.SIDES;
		int result = die.rollDie();
		assertTrue(result >= 1 && result <= sides);
	}

}
