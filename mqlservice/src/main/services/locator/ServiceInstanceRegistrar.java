package services.locator;

public interface ServiceInstanceRegistrar {
    ServiceTypeRegistrar asSingleInstance();
    ServiceTypeRegistrar asMultipleInstances();
}
