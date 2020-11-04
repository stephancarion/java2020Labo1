package be.technifutur.java2020.gestionStage.exception;

public class invalidEndDateTimeStageException extends StageException {
    public invalidEndDateTimeStageException() {
    }

    public invalidEndDateTimeStageException(String message) {
        super(message);
    }

    public invalidEndDateTimeStageException(String message, Throwable cause) {
        super(message, cause);
    }

    public invalidEndDateTimeStageException(Throwable cause) {
        super(cause);
    }

    public invalidEndDateTimeStageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
