package at.hackenbergerhampl.picalculator.interfaces;

import java.math.BigDecimal;
import java.rmi.RemoteException;

/**
 * A Calculator of PI
 * 
 * @author Burkhard Hampl
 * @version 1.0
 */
public interface Calculator {
	
	/**
	 * Compute the value of pi to the specified number of digits after the
	 * decimal point. The value is computed using Machin's formula:
	 *
	 * pi/4 = 4*arctan(1/5) - arctan(1/239)
	 *
	 * and a power series expansion of arctan(x) to sufficient precision.
	 * 
	 * @param digits digits of pi
	 * @return pi
	 * @throws RemoteException when there is a failure with the rmi connection
	 */
	public BigDecimal pi(int digits) throws RemoteException;

}
