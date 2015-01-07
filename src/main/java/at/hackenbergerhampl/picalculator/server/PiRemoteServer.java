package at.hackenbergerhampl.picalculator.server;

import at.hackenbergerhampl.picalculator.interfaces.RemoteCalculator;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PiRemoteServer extends UnicastRemoteObject implements RemoteCalculator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6197298247159975736L;

	public PiRemoteServer(String serverName) throws RemoteException {

	}

	public BigDecimal pi(int anzahl_nachkommastellen) {
		// TODO Auto-generated method stub
		return null;
	}

}
