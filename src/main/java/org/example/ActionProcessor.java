package org.example;

import org.example.domain.Action;

import java.util.List;

@FunctionalInterface
public interface ActionProcessor {
    List<Action> process(Action action, MergedContext mergedContext) throws Exception;

    default ExtraContext preprocessAll(List<Action> actions) {
        return new DummyExtraContext();
    }
}

