package test;

import static org.junit.Assert.*;
import mowItNow.Lawn;
import mowItNow.Mow;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MowTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMoveForward() {
		Mow mow = new Mow(1, 2, "N", 12);
		Lawn lawn = new Lawn(10, 10);
		assertEquals("", mow.moveForward("A", lawn));
	}

	@Test
	public void testMoveForwardEmpty() {
		Mow mow = new Mow(1, 2, "N", 12);
		Lawn lawn = new Lawn(10, 10);

		assertEquals("Command unknown, I stay here.", mow.moveForward("", lawn));
	}

	@Test
	public void testMoveForwardNull() {
		Mow mow = new Mow(1, 2, "N", 12);
		Lawn lawn = new Lawn(10, 10);

		assertEquals("Command doesn't understand, I stay here.",
				mow.moveForward(null, lawn));
	}

	@Test
	public void testMoveForwardOK() {
		Mow mow = new Mow(10, 2, "N", 12);
		Lawn lawn = new Lawn(10, 10);

		mow.moveForward("A", lawn);
		assertEquals(3, mow.getY());

		mow.setCardinal("W");
		mow.moveForward("A", lawn);
		assertEquals(9, mow.getX());

		mow.setCardinal("S");
		mow.moveForward("A", lawn);
		assertEquals(2, mow.getY());
	}

	@Test
	public void testMoveForwardOverTheLawn() {
		Mow mow = new Mow(10, 2, "E", 12);
		Lawn lawn = new Lawn(10, 10);

		assertEquals("I don't move.", mow.moveForward("A", lawn));
	}

	@Test
	public void testMoveForwardWrongCommand() {
		Mow mow = new Mow(1, 2, "N", 12);
		Lawn lawn = new Lawn(10, 10);
		assertEquals("Command unknown, I stay here.",
				mow.moveForward("D", lawn));
	}

	@Test
	public void testNoLawn() {
		Mow mow = new Mow(1, 2, "N", 12);
		Lawn lawn = null;
		assertFalse(mow.invariantCondition(lawn));
	}

	@Test
	public void testTurnLeftEmpty() {
		Mow mow = new Mow(10, 2, "N", 12);

		assertEquals("Command unknown, I stay here.", mow.turnLeft(""));
	}

	@Test
	public void testTurnLeftNull() {
		Mow mow = new Mow(10, 2, "N", 12);
		assertEquals("Command not understand, I stay here.", mow.turnLeft(null));
	}

	@Test
	public void testTurnLeftOK() {
		Mow mow = new Mow(10, 2, "N", 12);

		mow.turnLeft("G");
		assertEquals("W", mow.getCardinal());

		mow.turnLeft("G");
		assertEquals("S", mow.getCardinal());

		mow.turnLeft("G");
		assertEquals("E", mow.getCardinal());
	}

	@Test
	public void testTurnLeftWrongCommand() {
		Mow mow = new Mow(10, 2, "N", 12);

		assertEquals("Command unknown, I stay here.", mow.turnLeft("A"));
	}

	@Test
	public void testTurnRightEmpty() {
		Mow mow = new Mow(10, 2, "N", 12);

		assertEquals("Command unknown, I stay here.", mow.turnRight(""));
	}

	@Test
	public void testTurnRightNull() {
		Mow mow = new Mow(10, 2, "N", 12);

		assertEquals("Command not understand, I stay here.",
				mow.turnRight(null));
	}

	@Test
	public void testTurnRightOk() {
		Mow mow = new Mow(10, 2, "N", 12);

		mow.turnRight("D");
		assertEquals("E", mow.getCardinal());

		mow.turnRight("D");
		assertEquals("S", mow.getCardinal());

		mow.turnRight("D");
		assertEquals("W", mow.getCardinal());
	}

	@Test
	public void testTurnRightWrongCommand() {
		Mow mow = new Mow(10, 2, "N", 12);

		assertEquals("Command unknown, I stay here.", mow.turnRight("A"));
	}

	@Test
	public void testXSupToLawnLength() {
		Mow mow = new Mow(100, 2, "N", 12);
		Lawn lawn = new Lawn(10, 10);
		assertFalse(mow.invariantCondition(lawn));
	}

	@Test
	public void testYSupToLawnHeight() {
		Mow mow = new Mow(1, 200, "N", 12);
		Lawn lawn = new Lawn(10, 10);
		assertFalse(mow.invariantCondition(lawn));
	}

}
