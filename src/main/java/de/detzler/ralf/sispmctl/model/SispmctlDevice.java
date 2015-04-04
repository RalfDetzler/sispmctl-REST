package de.detzler.ralf.sispmctl.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import de.detzler.ralf.sispmctl.controller.Shell;

public class SispmctlDevice {

	@Autowired
	private List<Outlet> outlets;
	
	private Shell shell;

	public SispmctlDevice() {
		super();
	}

	public String getOutletStatus(int outlet) {
		assertIndexOk(outlet);
		return outlets.get(outlet).getStatus();
	}

	public int getNumOutlets() {
		return outlets.size() - 1;
	}

	public void switchOutletOn(int outlet) {
		assertIndexOk(outlet);
		outlets.get(outlet).switchOn();
	}

	public void switchOutletOff(int outlet) {
		assertIndexOk(outlet);
		outlets.get(outlet).switchOff();
	}

	public void assertIndexOk(int outlet) {
		if (outlet < 1 || outlet > getNumOutlets()) {
			throw new IllegalArgumentException("Outlet '" + outlet
					+ "' not allowed. Try outlet between 0 and "
					+ getNumOutlets() + " .");
		}
	}

	@Autowired
	@Qualifier("shell")
	public void setShell(Shell shell) {
		this.shell = shell;
		injectShellIntoOutlets(shell);
	}

	protected void injectShellIntoOutlets(Shell shell) {
		for (Outlet outlet : outlets) {
			outlet.setShell(shell);
		}
	}

	public List<Outlet> getOutlets() {
		return outlets;
	}

	public void setOutlets(List<Outlet> outlets) {
		this.outlets = outlets;
	}

	public Shell getShell() {
		return shell;
	}

}
