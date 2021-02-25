package rmi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IRemoteServer {

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

    public static void main(String... args) throws AccessException, RemoteException, AlreadyBoundException, MalformedURLException, IOException {
        System.out.println("Starting server...");

        URL url = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String ip = in.readLine();

        System.setProperty("java.rmi.server.hostname", ip);
        final IRemoteServer server = new Server();
        LocateRegistry.createRegistry(1099).bind("solver", UnicastRemoteObject.exportObject(server, 1100));

        System.out.println("Started server at //" + ip + ":1099/solver");
    }
}
