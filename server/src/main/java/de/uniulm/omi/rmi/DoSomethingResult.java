package de.uniulm.omi.rmi;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by daniel on 11.10.16.
 */
public class DoSomethingResult implements Serializable {

    private final long waitedFor;
    private final String uniqueId;

    public DoSomethingResult(long waitedFor) {
        this.waitedFor = waitedFor;
        this.uniqueId = UUID.randomUUID().toString();
    }

    public long waitedFor() {
        return waitedFor;
    }

    public String uuid() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return "DoSomethingResult{" +
                "waitedFor=" + waitedFor +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }
}
