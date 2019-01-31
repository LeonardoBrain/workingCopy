package ar.edu.iua.proyectoFinal.model.exception;

public class BusinessException extends Exception {


    public BusinessException(String message) {
        super(message);

    }


    public BusinessException() {

    }

    @Override
    public String getMessage() {
        return "Hubo un problema en el Request";
    }
}
