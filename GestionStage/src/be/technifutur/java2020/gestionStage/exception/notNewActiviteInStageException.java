package be.technifutur.java2020.gestionStage.exception;

public class notNewActiviteInStageException extends Exception {

    public notNewActiviteInStageException() {
    }

    public notNewActiviteInStageException(String message) {
        super(message);
    }

    public notNewActiviteInStageException(String message, Throwable cause) {
        super(message, cause);
    }

    public notNewActiviteInStageException(Throwable cause) {
        super(cause);
    }

    public notNewActiviteInStageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
