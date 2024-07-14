package org.example.reporting;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Action;

import java.util.List;

@Slf4j
public class DummyReportingService implements ReportingService {
    @Override
    public void reportSuccessful(List<Action> actions) {
        actions.forEach(action -> log.info("Action was successfully transformed: {}", action.id()));
    }

    @Override
    public void reportFailure(Action action, Exception exception) {
        log.warn("Action wasn't transformed: {}", action.id(), exception);
    }
}
