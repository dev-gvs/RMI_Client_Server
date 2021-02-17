package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteServer extends Remote {
    static final String SERVER_NAME = "solver";
    static final int PORT = 1101;
    
    EquationData solveEquation(EquationData data) throws RemoteException;
    void stopServer() throws RemoteException;
}
