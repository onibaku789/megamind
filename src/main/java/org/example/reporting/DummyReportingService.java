package org.example.reporting;

import org.example.domain.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DummyReportingService implements ReportingService {

    private static final Logger log = LoggerFactory.getLogger(DummyReportingService.class);

    @Override
    public void reportSuccessful(List<Action> actions) {
        actions.forEach(action -> log.info("Action was successfully transformed: {}", action.id()));
    }

    @Override
    public void reportFailure(Action action, Throwable exception) {
        log.warn("Action wasn't transformed: {}", action.id(), exception);
    }
}
