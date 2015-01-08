package at.hackenbergerhampl.picalculator.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import at.hackenbergerhampl.picalculator.error.InvalidOptionException;

/**
 * Main class of {@link PIRemoteServer}
 * 
 * @author Burkhard Hampl
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		CLIParser p = new CLIParser();
		try {
			p.parse(args, "java -jar PIRemoteServer.jar [options]");
		} catch (InvalidOptionException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}
		try {
			if (p.getHost() == null) {
			new PIRemoteServer(p.getPort());
			} else {
				new PIRemoteServer(p.getHost(), p.getPort());
			}
		} catch (RemoteException ex) {
			System.out.println(ex.getMessage());
		} catch (AlreadyBoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
