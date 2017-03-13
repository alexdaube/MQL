package services.locator;

public interface ServiceTypeRegistrar {
    <T> ServiceRegistrar of(Class<T> service);
}
