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
	 * @throws RemoteException 
	 */
	public void addRemoteCalculator(RemoteCalculator server) throws RemoteException;

	/**
	 * Removes a {@link RemoteCalculator} from the server list
	 * 
	 * @param server {@link RemoteCalculator} to remove
	 * @throws RemoteException 
	 */
	public boolean removeRemoteCalculator(RemoteCalculator server) throws RemoteException;

	/**
	 * Return the next {@link RemoteCalculator} in the list
	 * 
	 * @return next {@link RemoteCalculator} in the list
	 * @throws RemoteException 
	 */
	public RemoteCalculator getNextRemoteCalculator() throws RemoteException;

}
