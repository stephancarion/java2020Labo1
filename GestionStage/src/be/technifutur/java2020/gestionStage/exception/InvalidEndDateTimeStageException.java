package be.technifutur.java2020.gestionStage.exception;

public class InvalidEndDateTimeStageException extends StageException {
    public InvalidEndDateTimeStageException() {
    }

    public InvalidEndDateTimeStageException(String message) {
        super(message);
    }

    public InvalidEndDateTimeStageException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEndDateTimeStageException(Throwable cause) {
        super(cause);
    }

    public InvalidEndDateTimeStageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
