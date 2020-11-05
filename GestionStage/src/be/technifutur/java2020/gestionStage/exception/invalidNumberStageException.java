package be.technifutur.java2020.gestionStage.exception;

public class invalidNumberStageException extends Exception {
    public invalidNumberStageException() {
    }

    public invalidNumberStageException(String message) {
        super(message);
    }

    public invalidNumberStageException(String message, Throwable cause) {
        super(message, cause);
    }

    public invalidNumberStageException(Throwable cause) {
        super(cause);
    }

    public invalidNumberStageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
