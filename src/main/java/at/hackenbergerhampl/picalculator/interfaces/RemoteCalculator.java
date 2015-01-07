package at.hackenbergerhampl.picalculator.interfaces;

import java.io.Closeable;
import java.rmi.Remote;

public interface RemoteCalculator extends Calculator, Remote, Closeable {

}
