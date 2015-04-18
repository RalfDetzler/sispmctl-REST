package de.detzler.ralf.command;

public interface Command {
	public void execute();
	public void undo();
}
