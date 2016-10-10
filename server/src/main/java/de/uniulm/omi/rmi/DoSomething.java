package de.uniulm.omi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

/**
 * Created by daniel on 10.10.16.
 */
public interface DoSomething extends Remote {

    String registryID = "doSomething";

    void doSomething(long duration, TimeUnit timeUnit) throws RemoteException;

}
