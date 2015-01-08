package at.hackenbergerhampl.picalculator.client;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
