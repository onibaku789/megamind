package org.example.reporting;

import org.example.domain.Action;

import java.util.List;

public interface ReportingService {
    void reportSuccessful(List<Action> actions);

    void reportFailure(Action action, Exception exception);
}
