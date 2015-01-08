package at.hackenbergerhampl.picalculator.interfaces;

import java.io.Closeable;
import java.rmi.Remote;

/**
 * A PI Calculator which can be called from remote
 * 
 * @author Burkhard Hampl
 * @version 1.0
 */
public interface RemoteCalculator extends Calculator, Remote, Closeable {

}
