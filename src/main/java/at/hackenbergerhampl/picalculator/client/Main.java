package at.hackenbergerhampl.picalculator.client;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;

import at.hackenbergerhampl.picalculator.error.InvalidOptionException;
import at.hackenbergerhampl.picalculator.interfaces.RemoteCalculator;

/**
 * Main class of Client
 * 
 * @author Burkhard Hampl
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		CLIParser p = new CLIParser();
		try {
			p.parse(args, "java -jar PIClient.jar [options]");
		} catch (InvalidOptionException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}

		try {
			RemoteCalculator rb;
			rb = (RemoteCalculator) Naming.lookup("rmi://" + p.getHost() + ":" + p.getPort() + "/picalc");
			BigDecimal pi = rb.pi(p.getPiDigits());
			System.out.println(pi != null ? pi : "There is currently no calculation server available!");
		}catch (UnmarshalException ex) {
			System.out.println("Connection was closed unexpectedly!");
		} catch (RemoteException ex) {
			System.out.println("There is currently no calculation server available!");
		} catch (MalformedURLException e) {
			System.out.println("The host adress you entered is not valid!");
		} catch (NotBoundException e) {
			System.out.println("There is currently no calculation server available!");
		}
	}

}
