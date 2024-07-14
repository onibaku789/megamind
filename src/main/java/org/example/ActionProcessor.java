package org.example;

import org.example.domain.Action;

import java.util.List;
import java.util.Optional;

public interface ActionProcessor {
    List<Action> process(Action action, MergedContext mergedContext) throws Exception;

    default Optional<ExtraContext> preprocessAll(List<Action> actions) {
        return Optional.empty();
    }
}

