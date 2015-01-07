package at.hackenbergerhampl.picalculator.interfaces;

import java.rmi.Remote;

public interface RemoteBalancer extends Remote {

	public void addRemoteCalculator(RemoteCalculator server);

	public boolean removeRemoteCalculator(RemoteCalculator server);

	public RemoteCalculator getRandomCalculator();

}
