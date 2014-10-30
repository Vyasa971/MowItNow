package test;

import static org.junit.Assert.*;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import mowItNow.MowFileManagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MowFileManagementTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetLinesFromFile() throws IOException {
		MowFileManagement mowFM = new MowFileManagement();
		List<String> expectedList = new ArrayList<>();
		
		expectedList.add("55");
		expectedList.add("1 2 N");
		expectedList.add("GAGAGAGAA=");

		assertNotNull(mowFM.getLinesFromFile());
		assertEquals(expectedList.get(0), mowFM.getLinesFromFile().get(0));
		assertNotEquals(expectedList.get(2), mowFM.getLinesFromFile().get(2));
		
	}

}
