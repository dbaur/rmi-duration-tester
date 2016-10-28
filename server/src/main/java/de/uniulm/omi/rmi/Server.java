package de.uniulm.omi.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

/**
 * Created by daniel on 10.10.16.
 */
public class Server implements DoSomething {

    private static DoSomething stub;
    private static Registry registry;

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            stub = (DoSomething) UnicastRemoteObject.exportObject(obj, 33033);

            // Bind the remote object's stub in the registry
            registry = LocateRegistry.createRegistry(1099);
            registry.bind(DoSomething.registryID, stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public DoSomethingResult doSomething(long duration, TimeUnit timeUnit)
        throws DoSomethingException {
        System.out.println(
            String.format("Starting execution of doSomething for %s %s", duration, timeUnit));
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(timeUnit.toMillis(duration));
        } catch (InterruptedException e) {
            throw new DoSomethingException("Could not sleep, got interrupted.", e);
        }
        long end = System.currentTimeMillis();
        final DoSomethingResult doSomethingResult = new DoSomethingResult(end - start);
        System.out.println(
            String.format("Finished execution of doSomething. Result: %s.", doSomethingResult));
        return doSomethingResult;
    }
}
