package org.example.client;

import lombok.extern.slf4j.Slf4j;
import org.example.ActionProcessor;
import org.example.ExtraContext;
import org.example.MergedContext;
import org.example.domain.Action;

import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class ClientActionProcessor implements ActionProcessor {
    private final Random random = new Random();

    @Override
    public ExtraContext preprocessAll(List<Action> actions) {
        // Example preprocessing logic
        log.info("Preprocessing {} actions", actions.size());
        List<String> derivedEntities = actions.stream()
                .map(Action::getId)
                .collect(Collectors.toList());
        DerivedEntitiesContext derivedEntitiesContext = new DerivedEntitiesContext(derivedEntities);
        log.info("Creating {}", DerivedEntitiesContext.class);
        return derivedEntitiesContext;
    }

    @Override
    public List<Action> process(Action action, MergedContext mergedContext) throws Exception {
        // Client-specific processing logic
        log.info("Client processing action with ID: {}", action.getId());
        // Simulate processing logic
        if (random.nextBoolean()) {
            throw new Exception("Simulated processing failure");
        }
        return Collections.singletonList(action);
    }
}

