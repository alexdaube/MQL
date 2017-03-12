package services.locator;

import java.util.concurrent.Callable;

public class SingleInstance implements Instance {

    private final Object instance;

    public SingleInstance(Callable<Object> instance) {
        try {
            this.instance = instance.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object resolve() {
        return instance;
    }
}
