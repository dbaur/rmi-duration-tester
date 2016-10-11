package de.uniulm.omi.rmi;

import java.io.Serializable;

/**
 * Created by daniel on 11.10.16.
 */
public class DoSomethingResult implements Serializable {

    private final long waitedFor;

    public DoSomethingResult(long waitedFor) {
        this.waitedFor = waitedFor;
    }

    public long waitedFor() {
        return waitedFor;
    }

    @Override public String toString() {
        return "DoSomethingResult{" + "waitedFor=" + waitedFor + '}';
    }
}
