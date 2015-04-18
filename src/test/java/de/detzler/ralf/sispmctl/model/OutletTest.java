package de.detzler.ralf.sispmctl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.detzler.ralf.sispmctl.controller.ShellSimulator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml" })
public class OutletTest {

	private Outlet outlet;
	private ShellSimulator shell;
	
	@Before
	public void setUp() throws Exception {
		shell = new ShellSimulator();
		outlet = new Outlet(1);
		outlet.setShell(shell);
	}


	@Test
	public void testInvalidOutletConstructur() {
		try {
			Outlet test = new Outlet(2,"hello");
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
	}

	@Test
	public void testGetStatus() {
		
		outlet.setStatus(Outlet.OUTLET_OFF);
		shell.setExpectedStatusOf1ToOff();
		assertEquals(Outlet.OUTLET_OFF, outlet.getStatus());

		outlet.setStatus(Outlet.OUTLET_ON);
		shell.setExpectedStatusOf1ToOn();
		assertEquals(Outlet.OUTLET_ON, outlet.getStatus());

		outlet.setStatus(Outlet.OUTLET_OFF);
		shell.setExpectedStatusOf1ToOff();
		assertEquals(Outlet.OUTLET_OFF, outlet.getStatus());
	}

	@Test
	public void testSetInvalidStatus() {
		try {
			outlet.setStatus("hello");
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
	}

	@Test
	public void testSetStatusOn() {

		outlet.setStatus(Outlet.OUTLET_ON);
		assertEquals(Outlet.OUTLET_ON, outlet.getStatus());
	}

	@Test
	public void testSetStatusOff() {

		outlet.setStatus(Outlet.OUTLET_OFF);
		shell.setExpectedStatusOf1ToOff();
		assertEquals(Outlet.OUTLET_OFF, outlet.getStatus());
	}



}
