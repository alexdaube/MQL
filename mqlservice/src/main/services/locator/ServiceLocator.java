package services.locator;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class ServiceLocator implements BasicServiceLocator, ServiceInstanceRegistrar, ServiceTypeRegistrar {
    private static ServiceLocator instance;
    private Callable<Object> callable;
    private Instance serviceInstance;
    private HashMap<Class<?>, Instance> services;

    private ServiceLocator() {
        services = new HashMap<>();
    }

    public static BasicServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> service) {
        if (!services.containsKey(service)) {
            throw new UnregisteredServiceException(service);
        }
        return (T) services.get(service).resolve();
    }

    @Override
    public ServiceInstanceRegistrar register(Callable<Object> service) {
        this.callable = service;
        return this;
    }

    @Override
    public ServiceTypeRegistrar asSingleInstance() {
        this.serviceInstance = new SingleInstance(callable);
        return this;
    }

    @Override
    public ServiceTypeRegistrar asMultipleInstances() {
        this.serviceInstance = new MultipleInstance(callable);
        return this;
    }

    @Override
    public <T> ServiceRegistrar of(Class<T> instance) {
        if (services.containsKey(instance)) {
            throw new AlreadyRegisteredServiceException(instance);
        }
        services.put(instance, serviceInstance);
        return this;

    }
}
