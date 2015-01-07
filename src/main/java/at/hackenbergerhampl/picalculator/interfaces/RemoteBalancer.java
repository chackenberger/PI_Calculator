package at.hackenbergerhampl.picalculator.interfaces;

import java.rmi.Remote;

/**
 * A balancer for PI Calculation RMI requests
 * 
 * @author Hackenberger Christoph
 * @version 1.0
 */
public interface RemoteBalancer extends Remote {

	/**
	 * Adds a {@link RemoteCalculator} to the server list
	 * 
	 * @param server {@link RemoteCalculator} to add
	 */
	public void addRemoteCalculator(RemoteCalculator server);

	/**
	 * Removes a {@link RemoteCalculator} from the server list
	 * 
	 * @param server {@link RemoteCalculator} to remove
	 */
	public boolean removeRemoteCalculator(RemoteCalculator server);

	/**
	 * Return the next {@link RemoteCalculator} in the list
	 * 
	 * @return next {@link RemoteCalculator} in the list
	 */
	public RemoteCalculator getNextRemoteCalculator();

}
