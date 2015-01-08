package at.hackenbergerhampl.picalculator.server;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import at.hackenbergerhampl.picalculator.balancer.PIBalancer;
import at.hackenbergerhampl.picalculator.interfaces.RemoteBalancer;
import at.hackenbergerhampl.picalculator.interfaces.RemoteCalculator;

/**
 * Calculates Pi with a spacific number of digits
 * 
 * @author Burkhard Hampl
 * @version 1.0
 */
public class PIRemoteServer extends UnicastRemoteObject implements RemoteCalculator {

	private static final long serialVersionUID = -6197298247159975736L;

	/** constants used in pi computation */
	private static final BigDecimal FOUR = BigDecimal.valueOf(4);

	/** rounding mode to use during pi computation */
	private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;

	private Registry reg;

	/**
	 * Creates a new instance of a {@link PIRemoteServer} which connects to a
	 * {@link PIBalancer}
	 * 
	 * @param host
	 *            ip or domain of a {@link PIBalancer}
	 * @param port
	 *            port of a {@link PIBalancer}
	 * 
	 * @throws RemoteException
	 *             if the registry could not be exported
	 * @throws AlreadyBoundException
	 *             if there is another {@link PIBalancer} or
	 *             {@link PIRemoteServer} already bound
	 */
	public PIRemoteServer(String host, int port) throws RemoteException {
		super();
		try {
			RemoteBalancer rb;
			rb = (RemoteBalancer) Naming.lookup("rmi://" + host + ":" + port + "/picalc");
			rb.addRemoteCalculator(this);
		} catch (MalformedURLException e) {
			throw new RemoteException("The host adress you entered is not valid!");
		} catch (NotBoundException e) {
			throw new RemoteException("There is currently no Balancer available!");
		}
		System.out.println("Server successfully started!");
		System.out.println("Server successfully connected to Balancer!");
	}

	/**
	 * Creates a new instance of a {@link PIRemoteServer} which creates a local
	 * {@link Registry} and dose not connect to a {@link RemoteBalancer}
	 * 
	 * @param port
	 *            the port the registry should bind on
	 * 
	 * @throws RemoteException
	 *             if the registry could not be exported
	 * @throws AlreadyBoundException
	 *             if there is another {@link RemoteBalancer} or
	 *             {@link PIRemoteServer} already bound
	 */
	public PIRemoteServer(int port) throws RemoteException, AlreadyBoundException {
		super();
		this.reg = LocateRegistry.createRegistry(port);
		this.reg.bind("picalc", this);
		System.out.println("Server successfully started!");
		System.out.println("Registy successfully bounded!");
	}

	// Source http://homepage.uibk.ac.at/~csag8802/client/Pi.java

	public BigDecimal pi(int digits) throws RemoteException {
		System.out.println("Start calculating PI with " + digits + " digits");
		long ms = System.currentTimeMillis();
		int scale = digits + 5;
		BigDecimal arctan1_5 = arctan(5, scale);
		BigDecimal arctan1_239 = arctan(239, scale);
		BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
		System.out.println("Finished calculation with " + (System.currentTimeMillis() - ms) + "ms");
		return pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Compute the value, in radians, of the arctangent of the inverse of the
	 * supplied integer to the specified number of digits after the decimal
	 * point. The value is computed using the power series expansion for the arc
	 * tangent:
	 *
	 * arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 + (x^9)/9 ...
	 */
	protected static BigDecimal arctan(int inverseX, int scale) {
		BigDecimal result, numer, term;
		BigDecimal invX = BigDecimal.valueOf(inverseX);
		BigDecimal invX2 = BigDecimal.valueOf(inverseX * inverseX);

		numer = BigDecimal.ONE.divide(invX, scale, roundingMode);

		result = numer;
		int i = 1;
		do {
			numer = numer.divide(invX2, scale, roundingMode);
			int denom = 2 * i + 1;
			term = numer.divide(BigDecimal.valueOf(denom), scale, roundingMode);
			if ((i % 2) != 0) {
				result = result.subtract(term);
			} else {
				result = result.add(term);
			}
			i++;
		} while (term.compareTo(BigDecimal.ZERO) != 0);
		return result;
	}

	public void close() {
		try {
			UnicastRemoteObject.unexportObject(this, true);
			this.reg.unbind("picalc");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
