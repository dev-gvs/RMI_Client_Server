package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteServer extends Remote {

    EquationData solveEquation(EquationData data) throws RemoteException;
}
