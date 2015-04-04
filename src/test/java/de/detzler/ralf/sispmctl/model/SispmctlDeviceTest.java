package de.detzler.ralf.sispmctl.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.detzler.ralf.sispmctl.controller.ShellSimulator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
public class SispmctlDeviceTest {

	@Autowired
	private SispmctlDevice device;

	@Before
	public void setUp() throws Exception {
		ShellSimulator shell = (ShellSimulator) device.getShell();
		shell.init();
	}

	@Test
	public void testSetOutletsOn() {

		device.switchOutletOn(1);
		assertEquals(Outlet.OUTLET_ON, device.getOutletStatus(1));
		device.switchOutletOn(2);
		assertEquals(Outlet.OUTLET_ON, device.getOutletStatus(2));
		device.switchOutletOn(3);
		assertEquals(Outlet.OUTLET_ON, device.getOutletStatus(3));
		device.switchOutletOn(4);
		assertEquals(Outlet.OUTLET_ON, device.getOutletStatus(4));
	}

	@Test
	public void testInvalidSetOutletsOn() {
		try {
			device.switchOutletOn(-1);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
		try {
			device.switchOutletOn(0);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
		try {
			device.switchOutletOn(5);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
	}

	@Test
	public void testInvalidSetOutletsOff() {
		try {
			device.switchOutletOff(-1);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
		try {
			device.switchOutletOff(0);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
		try {
			device.switchOutletOff(5);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
	}

	@Test
	public void testSetOutletsOff() {
		ShellSimulator shell = (ShellSimulator) device.getShell();
		shell.setExpectedStatusOf1ToOff();
		device.switchOutletOff(1);
		assertEquals(Outlet.OUTLET_OFF, device.getOutletStatus(1));

		shell.setExpectedStatusOf2ToOff();
		device.switchOutletOff(2);
		assertEquals(Outlet.OUTLET_OFF, device.getOutletStatus(2));

		shell.setExpectedStatusOf3ToOff();
		device.switchOutletOff(3);
		assertEquals(Outlet.OUTLET_OFF, device.getOutletStatus(3));

		shell.setExpectedStatusOf4ToOff();
		device.switchOutletOff(4);
		assertEquals(Outlet.OUTLET_OFF, device.getOutletStatus(4));
	}

	@Test
	public void testConstructor() {
		assertEquals(4, device.getNumOutlets());
	}


	@Test
	public void testGetOutletStatusIndex() {
		try {
			device.getOutletStatus(-1);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}

		try {
			device.getOutletStatus(0);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}

		try {
			device.getOutletStatus(5);
			fail("Expected IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException ex) {
			;
		}
	}

}
