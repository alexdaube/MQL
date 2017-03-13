package services.locator;

import java.util.concurrent.Callable;

public interface ServiceRegistrar {
    ServiceInstanceRegistrar register(Callable<Object> callable);
}
