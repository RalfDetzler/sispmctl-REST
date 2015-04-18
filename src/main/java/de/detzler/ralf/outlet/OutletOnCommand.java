package de.detzler.ralf.outlet;

import de.detzler.ralf.command.Command;

public class OutletOnCommand implements Command {

	private Outlet outlet;

	public OutletOnCommand(Outlet outlet) {
		this.outlet = outlet;
	}

	@Override
	public void execute() {
		outlet.on();
	}

	@Override
	public void undo() {
		outlet.off();
	}
}
