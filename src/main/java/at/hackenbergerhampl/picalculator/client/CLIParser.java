package at.hackenbergerhampl.picalculator.client;

import java.io.ByteArrayOutputStream;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import at.hackenbergerhampl.picalculator.error.InvalidOptionException;

public class CLIParser {

	@Option(name = "-p", usage = "the port on which the PIBalancer listen on (default 1099)")
	private int port = 1099;

	@Option(name = "-h", usage = "the ip or domain of the PIBalancer")
	private String host;

	@Option(name = "-n", usage = "the specified number of digits after the decimal point of pi", required = true)
	private int piDigits;

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

	public String getHost() {
		return this.getHost();
	}

	public int getPort() {
		return this.port;
	}

	public int getPiDigits() {
		return this.piDigits;
	}

}
