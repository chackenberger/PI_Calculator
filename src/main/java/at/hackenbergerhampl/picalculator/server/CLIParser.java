package at.hackenbergerhampl.picalculator.server;

import java.io.ByteArrayOutputStream;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import at.hackenbergerhampl.picalculator.error.InvalidOptionException;

/**
 * A Parser to parse options and arguments from the CLI for {@link PIRemoteServer}
 * 
 * @author Burkhard Hampl
 * @version 1.0
 */
public class CLIParser {

	
	

	@Option(name = "-p", usage = "the port on which the PIRemoteServer should bind on or the port on which the PIBalancer listen on (default 1099)")
	private int port = 1099;

	@Option(name = "-h", usage = "the ip or domain where the PIBalancer is")
	private String host;

	
	/**
	 * This method starts the parsing of the options and arguments
	 *
	 * @param args
	 *            the arguments from the main method
	 * @param usage
	 *            a usage String of how to start the program
	 * @throws InvalidOptionException
	 *             if any thing goes wrong while parsing (ex: a required option
	 *             is missing)
	 */
	public void parse(String[] args, String usage) throws InvalidOptionException {
		CmdLineParser parser = new CmdLineParser(this);

		try {
			parser.parseArgument(args);
		} catch (CmdLineException ex) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			parser.printUsage(out);
			throw new InvalidOptionException(ex.getMessage() + '\n' + usage + '\n' + out.toString());
		}
	}

	public int getPort() {
		return this.port;
	}

	public String getHost() {
		return this.host;
	}

}
