package at.hackenbergerhampl.picalculator.interfaces;

import java.rmi.Remote;

public interface RemoteBalancer extends Remote {

	public abstract void addRemoteCalculator(RemoteCalculator server);

	public abstract boolean removeRemoteCalculator(RemoteCalculator server);

	public abstract RemoteCalculator getRandomCalculator();

}
