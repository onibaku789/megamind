package org.example.client;

import org.example.ActionProcessor;
import org.example.context.ExtraContext;
import org.example.context.MergedContext;
import org.example.domain.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class ClientActionProcessor implements ActionProcessor {

    private static final Logger log = LoggerFactory.getLogger(ClientActionProcessor.class);
    private final Random random = new Random();

    @Override
    public Optional<ExtraContext> preprocessAll(List<Action> actions) {
        // Example preprocessing logic
        log.info("Preprocessing {} actions", actions.size());
        List<String> derivedEntities = actions.stream()
                .map(Action::id)
                .collect(Collectors.toList());
        DerivedEntitiesContext derivedEntitiesContext = ImmutableDerivedEntitiesContext.builder().derivedEntities(derivedEntities).build();
        log.info("Creating {}", DerivedEntitiesContext.class);
        return Optional.of(derivedEntitiesContext);
    }

    @Override
    public List<Action> process(Action action, MergedContext mergedContext) throws Exception {
        // Client-specific processing logic
        //todo: Use sealed interfaces, classes and delegate on the class type
        log.info("{}", mergedContext);
        log.info("Client processing action with ID: {}", action.id());
        // Simulate processing logic
        if (random.nextBoolean()) {
            throw new Exception("Simulated processing failure");
        }
        return Collections.singletonList(action);
    }
}

