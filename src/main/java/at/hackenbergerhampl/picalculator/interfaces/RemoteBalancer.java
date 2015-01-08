package at.hackenbergerhampl.picalculator.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	 * @throws RemoteException when there is a failure with the rmi connection
	 */
	public void addRemoteCalculator(RemoteCalculator server) throws RemoteException;

	/**
	 * Removes a {@link RemoteCalculator} from the server list
	 * 
	 * @param server {@link RemoteCalculator} to remove
	 * @throws RemoteException when there is a failure with the rmi connection
	 */
	public boolean removeRemoteCalculator(RemoteCalculator server) throws RemoteException;

	/**
	 * Return the next {@link RemoteCalculator} in the list
	 * 
	 * @return next {@link RemoteCalculator} in the list
	 * @throws RemoteException when there is a failure with the rmi connection
	 */
	public RemoteCalculator getNextRemoteCalculator() throws RemoteException;

}
