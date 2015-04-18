package de.detzler.ralf.sispmctl.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Shell {
	
	public List<String> executeCommand(String command) {

		List<String>output = new ArrayList<String>();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

}
