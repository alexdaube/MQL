package services.locator;

public interface ServiceResolver {
    <T> T resolve(Class<T> service);
}
