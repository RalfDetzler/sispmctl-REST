package de.detzler.ralf.outlet;

public class Outlet {
	
	private int channel;

	public Outlet(int channel) {
		this.channel = channel;
	}

	public void on() {
		System.out.println("outlet " + channel + " switched on");
	}
	
	public void off() {
		System.out.println("outlet " + channel + " switched off");
	}

}
