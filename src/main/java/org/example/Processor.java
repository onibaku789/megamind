package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.domain.Action;
import org.example.reporting.ReportingService;
import org.example.map.DtoToPciTransformer;
import org.example.map.PciToDtoTransformer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Processor {
    private final ActionProcessor actionProcessor;
    private final ReportingService reportingService;
    private final DtoToPciTransformer dtoToPciTransformer;
    private final PciToDtoTransformer dtoTransformer;

    protected Processor(ActionProcessor actionProcessor,
                        ReportingService reportingService,
                        DtoToPciTransformer dtoToPciTransformer,
                        PciToDtoTransformer dtoTransformer) {
        this.actionProcessor = actionProcessor;
        this.reportingService = reportingService;
        this.dtoToPciTransformer = dtoToPciTransformer;
        this.dtoTransformer = dtoTransformer;
    }

    public Manifest processZip(Manifest zip, BasicContext basicContext) {
        List<Action> transform = dtoTransformer.transform(zip);
        List<Action> actions = processAll(transform, basicContext);
        return dtoToPciTransformer.transform(actions);
    }

    public List<Action> processAll(List<Action> actions, BasicContext basicContext) {
        MergedContext mergedContext = new MergedContext(basicContext, actionProcessor.preprocessAll(actions));

        return actions.stream()
                .map(action -> tryToProcess(action, mergedContext))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Action> tryToProcess(Action action, MergedContext mergedContext) {
        try {
            log.info("Started processing action: {}", action);
            log.info("Basic context contains: [{}] ExtraContext contains: [{}]", mergedContext.basicContext(), mergedContext.extraContext());
            var processedActions = actionProcessor.process(action, mergedContext);
            log.info("Finished processing action: {}", action);
            reportingService.reportSuccessful(processedActions);
            return processedActions;
        } catch (Exception e) {
            reportingService.reportFailure(action, e);
            log.warn("Action dropped: {}", action, e);
        }
        return Collections.emptyList();
    }

}

