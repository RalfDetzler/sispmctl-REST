package de.detzler.ralf.sispmctl.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import de.detzler.ralf.sispmctl.controller.Shell;

/**
 * <pre>
 * sudo sispmctl -A4 --Aat "m2015-04-01 09:15" --Ado on --Aat "2015-03-31 21:15" --Ado off --Aloop 1440
 * </pre>
 * 
 * @author D020715
 * 
 */
public class Outlet {

	public static final String OUTLET_OFF = "off";
	public static final String OUTLET_ON = "on";

	private String status;
	final private int channel;

	@Autowired
	private Shell shell;
	
	public Outlet(int channel) {
		this.channel = channel;
		setStatus(OUTLET_OFF);
	}

	public Outlet(int channel, String status) {
		super();
		this.channel = channel;
		setStatus(status);
	}

	public String getStatus() {

		String cmd = "sispmctl -g " + getChannel();
		List<String> result = shell.executeCommand(cmd);

		if (result.size() != 2) {
			return "ERROR: executing command '" + cmd + "'. Result: "
					+ result.get(0);
		}

		int indexOf = result.get(1).indexOf(":");
		status = result.get(1).substring(indexOf + 2);
		return status;
	}

	public void setStatus(String status) {

		if (!status.equals(OUTLET_OFF) && !status.equals(OUTLET_ON)) {
			throw new IllegalArgumentException(
					"Status '"
							+ status
							+ "' not allowed. Try 'Outlet.OUTLET_ON' or 'Outlet.OUTLET_OFF'.");
		}

		this.status = status;
	}

	public int getChannel() {
		return channel;
	}

	public void switchOn() {

		String cmd = "sispmctl -o " + getChannel();
		List<String> result = shell.executeCommand(cmd);

		int indexOf = result.get(1).lastIndexOf(" ");
		status = result.get(1).substring(indexOf + 1);

		System.out.println(cmd);
		setStatus(status);
	}

	public void switchOff() {
		
		String cmd = "sispmctl -f " + getChannel();
		List<String> result = shell.executeCommand(cmd);

		int indexOf = result.get(1).lastIndexOf(" ");
		status = result.get(1).substring(indexOf + 1);

		System.out.println(cmd);
		setStatus(status);
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

}
