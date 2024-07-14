package org.example;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(overshadowImplementation = true)
public interface BasicContext {
    @Value.Parameter
    String additionalInformation();


    static ImmutableBasicContext.Builder builder() {
        return ImmutableBasicContext.builder();
    }
}
