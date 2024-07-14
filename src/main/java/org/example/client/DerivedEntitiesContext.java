package org.example.client;

import org.example.context.ExtraContext;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface DerivedEntitiesContext extends ExtraContext {
    List<String> derivedEntities();
}
