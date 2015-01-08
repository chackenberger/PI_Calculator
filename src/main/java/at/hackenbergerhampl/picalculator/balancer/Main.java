package at.hackenbergerhampl.picalculator.balancer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import at.hackenbergerhampl.picalculator.error.InvalidOptionException;

/**
 * Main class for the Balancer
 * 
 * @author Hackenberger Christoph
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		CLIParser p = new CLIParser();
		try{
			p.parse(args, "java -jar PIBalancer.jar [options]");
		}catch (InvalidOptionException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);;
		}
		try {
			new PIBalancer(p.getPort());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
