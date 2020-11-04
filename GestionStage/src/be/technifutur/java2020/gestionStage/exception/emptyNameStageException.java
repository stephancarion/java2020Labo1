package be.technifutur.java2020.gestionStage.exception;

public class emptyNameStageException extends StageException {
    public emptyNameStageException() {
    }

    public emptyNameStageException(String message) {
        super(message);
    }

    public emptyNameStageException(String message, Throwable cause) {
        super(message, cause);
    }

    public emptyNameStageException(Throwable cause) {
        super(cause);
    }

    public emptyNameStageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
