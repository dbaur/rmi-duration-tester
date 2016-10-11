package de.uniulm.omi.rmi;

/**
 * Created by daniel on 11.10.16.
 */
public class DoSomethingException extends Exception {

    public DoSomethingException() {
    }

    public DoSomethingException(String s) {
        super(s);
    }

    public DoSomethingException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DoSomethingException(Throwable throwable) {
        super(throwable);
    }

    public DoSomethingException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
