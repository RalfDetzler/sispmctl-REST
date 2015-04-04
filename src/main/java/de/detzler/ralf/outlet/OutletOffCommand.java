package de.detzler.ralf.outlet;

import de.detzler.ralf.command.Command;

public class OutletOffCommand implements Command {

	private Outlet outlet;

	public OutletOffCommand(Outlet outlet) {
		this.outlet = outlet;
	}

	@Override
	public void execute() {
		outlet.off();
	}

	@Override
	public void undo() {
		outlet.on();
	}
}
