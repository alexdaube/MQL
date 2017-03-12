package services.locator;

public class AlreadyRegisteredServiceException extends RuntimeException {

    public <T> AlreadyRegisteredServiceException(Class<T> service) {
        super("A implementation for the service '" + service.getCanonicalName() + "' is already present.");
    }
}
