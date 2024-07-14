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
        DerivedEntitiesContext derivedEntitiesContext = ImmutableDerivedEntitiesContext.builder()
                .derivedEntities(derivedEntities)
                .build();
        log.info("Creating {}", derivedEntitiesContext.getClass());
        return Optional.of(derivedEntitiesContext);
    }

    @Override
    public List<Action> process(Action action, MergedContext mergedContext) throws Exception {
        log.info("Client processing action with ID");
        // Client can obtain their extra context information they collected in the overridden preprocessing method
        DerivedEntitiesContext derivedEntitiesContext = mergedContext.extraContext()
                .map(DerivedEntitiesContext.class::cast)
                .orElseThrow();
        // Simulate processing logic
        // need to delegate by class type or a type field
        if (random.nextBoolean()) {
            throw new Exception("Simulated processing failure");
        }
        return Collections.singletonList(action);
    }

    private boolean isDerivedEntitiesContext(ExtraContext extraContext) {
        return extraContext instanceof DerivedEntitiesContext;
    }
}

