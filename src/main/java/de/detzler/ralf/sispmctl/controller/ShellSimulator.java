package de.detzler.ralf.sispmctl.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShellSimulator extends Shell {

	Map<String, String[]> results = new HashMap<String, String[]>();

	public ShellSimulator() {
		
		init();
	}

	public void init() {
		setExpectedStatusOf1ToOn();
		setExpectedStatusOf2ToOn();
		setExpectedStatusOf3ToOn();
		setExpectedStatusOf4ToOn();

		results.put("sispmctl -f 1",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 1 off" });
		results.put("sispmctl -f 2",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 2 off" });
		results.put("sispmctl -f 3",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 3 off" });
		results.put("sispmctl -f 4",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 4 off" });
		
		results.put("sispmctl -o 1",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 1 on" });
		results.put("sispmctl -o 2",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 2 on" });
		results.put("sispmctl -o 3",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 3 on" });
		results.put("sispmctl -o 4",
				new String[] { "Accessing Gembird #0 USB device 005",
						"Switched outlet 4 on" });
	}

	public void setExpectedStatusOf4ToOn() {
		results.put("sispmctl -g 4", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 4:	on" });
	}
	
	public void setExpectedStatusOf4ToOff() {
		results.put("sispmctl -g 4", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 4:	off" });
	}

	public void setExpectedStatusOf3ToOn() {
		results.put("sispmctl -g 3", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 3:	on" });
	}

	public void setExpectedStatusOf3ToOff() {
		results.put("sispmctl -g 3", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 3:	off" });
	}
	
	public void setExpectedStatusOf2ToOn() {
		results.put("sispmctl -g 2", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 2:	on" });
	}
	
	public void setExpectedStatusOf2ToOff() {
		results.put("sispmctl -g 2", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 2:	off" });
	}
	
	public void setExpectedStatusOf1ToOn() {
		results.put("sispmctl -g 1", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 1:	on" });
	}
	
	public void setExpectedStatusOf1ToOff() {
		results.put("sispmctl -g 1", new String[] {
				"Accessing Gembird #0 USB device 017",
				"Status of outlet 1:	off" });
	}

	public List<String> executeCommand(String command) {

		String[] strings = results.get(command);
		return Arrays.asList(strings);
	}
}
