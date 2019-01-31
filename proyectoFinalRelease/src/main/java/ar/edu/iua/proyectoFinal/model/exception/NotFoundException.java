package ar.edu.iua.proyectoFinal.model.exception;

public class NotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();

    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public NotFoundException(String message) {
        super(message);

    }

    public NotFoundException(Throwable cause) {
        super(cause);

    }

    @Override
    public String getMessage() {
        return "No se encontro lo que se solicito";
    }
}
