package org.example.context;

import org.example.BasicContext;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(overshadowImplementation = true)
public interface MergedContext {
    BasicContext basicContext();

    Optional<ExtraContext> extraContext();

    static ImmutableMergedContext.Builder builder() {
        return ImmutableMergedContext.builder();
    }

}
