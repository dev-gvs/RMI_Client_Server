package rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server implements IRemoteServer {

    private static AtomicBoolean stopServer = new AtomicBoolean(false);

    @Override
    public EquationData solveEquation(EquationData data) throws RemoteException {
        try {
            System.out.println("Got " + data + " from " + UnicastRemoteObject.getClientHost());
            double x = data.getX();
            if (x <= 4) {
                double a = data.getSecondArgument();
                double y1 = ((a * a) / (x * x));
                double y2 = (6 * x);
                data.setY((y1 + y2));
            } else {
                double b = data.getSecondArgument();
                double y1 = (b * b);
                double y2 = ((4 + x) * (4 + x));
                data.setY((y1 * y2));
            }
        } catch (ServerNotActiveException e) {
        }
        return data;
    }

    @Override
    public void stopServer() throws RemoteException {
        stopServer.set(true);
    }
    
    public static void main(String... args) throws AccessException, RemoteException, AlreadyBoundException {
        System.out.println("Starting server...");
        final IRemoteServer server = new Server();
        LocateRegistry.createRegistry(IRemoteServer.PORT).bind(IRemoteServer.SERVER_NAME, UnicastRemoteObject.exportObject(server, 0));
        while (!stopServer.get()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Server stopped");
        System.exit(0);
    }

}
