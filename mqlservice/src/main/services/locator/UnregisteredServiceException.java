package services.locator;

public class UnregisteredServiceException extends RuntimeException {

    public <T> UnregisteredServiceException(Class<T> service) {
        super("Cannot find service name '" + service.getCanonicalName() + "'. Did you register it?");
    }
}
