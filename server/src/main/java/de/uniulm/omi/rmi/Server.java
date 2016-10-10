package de.uniulm.omi.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

/**
 * Created by daniel on 10.10.16.
 */
public class Server implements DoSomething {

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            DoSomething stub = (DoSomething) UnicastRemoteObject.exportObject(obj, 33033);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind(DoSomething.registryID, stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void doSomething(long duration, TimeUnit timeUnit) {
        try {
            Thread.sleep(timeUnit.toMillis(duration));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
