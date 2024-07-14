package org.example.client;

import org.example.ExtraContext;

import java.util.List;


public record DerivedEntitiesContext(List<String> derivedEntities) implements ExtraContext {

}
