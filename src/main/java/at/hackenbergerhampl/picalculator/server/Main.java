package at.hackenbergerhampl.picalculator.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import at.hackenbergerhampl.picalculator.error.InvalidOptionException;

public class Main {
	public static void main(String[] args) {
		CLIParser p = new CLIParser();
		try {
			p.parse(args, "java -jar PIRemoteServer.jar [options]");
		} catch (InvalidOptionException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
			;
		}
		try {
			if (p.getHost() == null) {
			new PIRemoteServer(p.getPort());
			} else {
				new PIRemoteServer(p.getHost(), p.getPort());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
