package top.auntie.exception;

public class ParametersException extends Exception {

    public ParametersException() {
    }

    public ParametersException(String msg) {
        super(msg);
    }

    public ParametersException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
