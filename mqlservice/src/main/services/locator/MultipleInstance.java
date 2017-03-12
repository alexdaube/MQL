package services.locator;

import java.util.concurrent.Callable;

public class MultipleInstance implements Instance {

    private final Callable<Object> callable;

    public MultipleInstance(Callable<Object> callable) {
        this.callable = callable;
    }

    @Override
    public Object resolve() {
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
