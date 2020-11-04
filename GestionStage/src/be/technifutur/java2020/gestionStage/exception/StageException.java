package be.technifutur.java2020.gestionStage.exception;

public class StageException extends Exception {
    public StageException() {
    }

    public StageException(String message) {
        super(message);
    }

    public StageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StageException(Throwable cause) {
        super(cause);
    }

    public StageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
