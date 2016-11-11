package de.uniulm.omi.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.*;

/**
 * Created by daniel on 10.10.16.
 */
public class Client {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static final long OVERHEAD = 2;
    private static Registry registry;
    private static DoSomething stub;

    private Client() {

    }

    private static void doSomething(final DoSomething stub, final long duration,
                                    final TimeUnit timeUnit) {

        Future<DoSomethingResult> future =
                executorService.submit(new Callable<DoSomethingResult>() {
                    public DoSomethingResult call() throws Exception {
                        System.out.println(String.format("Starting RMI call using stub %s, duration %s and timeUnit %s.", stub, duration, timeUnit));
                        return stub.doSomething(duration, timeUnit);
                    }
                });
        try {
            final DoSomethingResult doSomethingResult = future.get(duration * OVERHEAD, timeUnit);
            System.out.println(String
                    .format("Successfully executed task for %s %s. Got result %s.", duration, timeUnit,
                            doSomethingResult));
        } catch (InterruptedException e) {
            System.err.println("Got interrupted.");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("Error during execution of doSomething: " + e.getMessage());
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.err.println(
                    String.format("Task for %s %s failed due to timeout!", duration, timeUnit));
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try {
            registry = LocateRegistry.getRegistry(host, 1099);

            stub = (DoSomething) registry.lookup(DoSomething.registryID);

            TimeUnit timeUnit = TimeUnit.MINUTES;
            long start = 1;
            long interval = 5;
            long end = 60;

            while (start <= end) {
                doSomething(stub, start, timeUnit);
                start += interval;
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


