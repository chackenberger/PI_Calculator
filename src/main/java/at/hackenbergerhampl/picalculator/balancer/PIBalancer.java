package at.hackenbergerhampl.picalculator.balancer;

import at.hackenbergerhampl.picalculator.interfaces.RemoteBalancer;
import at.hackenbergerhampl.picalculator.interfaces.RemoteCalculator;

import java.math.BigDecimal;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class PIBalancer extends UnicastRemoteObject implements RemoteCalculator, RemoteBalancer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1973999418059633612L;
	private List servers;
	private Registry reg;
	
	public PIBalancer(int port) throws RemoteException {
		super();
		reg = LocateRegistry.createRegistry(port);
		try {
			reg.bind("picalc", this);
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BigDecimal pi(int anzahl_nachkommastellen) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addRemoteCalculator(RemoteCalculator server) {
		// TODO Auto-generated method stub
		
	}

	public boolean removeRemoteCalculator(RemoteCalculator server) {
		// TODO Auto-generated method stub
		return false;
	}

	public RemoteCalculator getRandomCalculator() {
		// TODO Auto-generated method stub
		return null;
	}

}
