package tingeso_mingeso.backendcuotasservice.Excepciones;

public class CuotaPagoNotFoundException extends RuntimeException {

    public CuotaPagoNotFoundException(String message) {
        super(message);
    }

    public CuotaPagoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
