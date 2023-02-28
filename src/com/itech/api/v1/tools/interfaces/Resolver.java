package com.itech.api.v1.tools.interfaces;

import java.util.function.Function;

public interface Resolver {
    Resolver then(Function callback);
    Exception exception(Function<Exception, Object> callback);
}
