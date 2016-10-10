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

    private Client() {

    }

    public static void doSomething(final DoSomething stub, final long duration,
        final TimeUnit timeUnit) {

        Future<Void> future = executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {
                stub.doSomething(duration, timeUnit);
                return null;
            }
        });
        try {
            future.get(duration * OVERHEAD, timeUnit);
            System.out.println(
                String.format("Successfully executed task for %s %s.", duration, timeUnit));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.err.println(
                String.format("Task for %s %s failed due to timeout!", duration, timeUnit));
        }
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try {
            Registry registry = LocateRegistry.getRegistry(host, 1099);

            DoSomething stub = (DoSomething) registry.lookup(DoSomething.registryID);

            TimeUnit timeUnit = TimeUnit.MINUTES;
            long start = 5;
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


