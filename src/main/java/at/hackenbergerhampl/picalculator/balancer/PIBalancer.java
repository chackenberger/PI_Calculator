package at.hackenbergerhampl.picalculator.balancer;

import at.hackenbergerhampl.picalculator.interfaces.RemoteBalancer;
import at.hackenbergerhampl.picalculator.interfaces.RemoteCalculator;
import at.hackenbergerhampl.picalculator.server.PIRemoteServer;

import java.math.BigDecimal;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a {@link PIBalancer} which balances request for pi calculation and
 * direct them to a calculation server
 * 
 * @author Hackenberger Christoph
 * @version 1.0
 */
public class PIBalancer extends UnicastRemoteObject implements RemoteCalculator, RemoteBalancer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1973999418059633612L;
	private ConcurrentLinkedQueue<RemoteCalculator> servers = new ConcurrentLinkedQueue<>();
	private Registry reg;

	/**
	 * Creates a new instance of a {@link PIBalancer}
	 * 
	 * @param port
	 *            the port the registry should bind on
	 * @throws RemoteException
	 *             if the registry could not be exported
	 * @throws AlreadyBoundException
	 *             if there is another {@link PIBalancer} or
	 *             {@link PIRemoteServer} is already bound
	 */
	public PIBalancer(int port) throws RemoteException, AlreadyBoundException {
		super();
		reg = LocateRegistry.createRegistry(port);
		reg.bind("picalc", this);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				close();
			}
		}));
		System.out.println("Balancer successfully started!");
		System.out.println("Registy successfully bounded!");
	}

	/**
	 * Delegates the pi calculation to one of his calculation servers
	 * 
	 * @param digits
	 *            digits of pi
	 * @return pi
	 */
	public BigDecimal pi(int digits) throws RemoteException {
		System.out.println("PI request with " + digits + " digits received. Directing to server!");
		while (true) {
			RemoteCalculator rc = this.getNextRemoteCalculator();
			if (rc != null) {
				try {
					return rc.pi(digits);
				} catch (RemoteException ex) {
					servers.remove(rc);
				}
			} else
				return null;
		}
	}

	public void addRemoteCalculator(RemoteCalculator server) throws RemoteException {
		servers.add(server);

	}

	public boolean removeRemoteCalculator(RemoteCalculator server) throws RemoteException {
		return servers.remove(server);
	}

	public RemoteCalculator getNextRemoteCalculator() throws RemoteException {
		RemoteCalculator rc = servers.poll();
		if (rc != null)
			servers.add(rc);
		return rc;
	}

	public void close() {
		try {
			reg.unbind("picalc");
			UnicastRemoteObject.unexportObject(reg, true);
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
	}
}
