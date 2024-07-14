package org.example;


import org.example.api.Manifest;
import org.example.context.MergedContext;
import org.example.domain.Action;
import org.example.domain.DeleteAction;
import org.example.reporting.ReportingService;
import org.example.map.DtoToPciTransformer;
import org.example.map.PciToDtoTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Processor {

    public static final String INPUT_ZIP_NAME = "input.zip.name";
    private static final Logger log = LoggerFactory.getLogger(Processor.class);
    //Todo explode me
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
        MDC.put(INPUT_ZIP_NAME, UUID.randomUUID().toString());
        List<Action> transform = dtoTransformer.transform(zip);
        List<Action> actions = processAll(transform, basicContext);
        Manifest transform1 = dtoToPciTransformer.transform(actions);
        MDC.remove(INPUT_ZIP_NAME);
        return transform1;
    }

    public List<Action> processAll(List<Action> actions, BasicContext basicContext) {
        MDC.put(INPUT_ZIP_NAME, UUID.randomUUID().toString());
        MergedContext mergedContext = MergedContext.builder()
                .basicContext(basicContext)
                .extraContext(actionProcessor.preprocessAll(actions))
                .build();

        List<Action> collect = actions.stream()
                .map(action -> tryToProcess(action, mergedContext))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        MDC.remove(INPUT_ZIP_NAME);
        return collect;
    }

    private List<Action> tryToProcess(Action action, MergedContext mergedContext) {
        MDC.put("rootUri", action.id());
        MDC.put("actionType", getActionType(action));
        try {
            log.info("Started processing action");
            List<Action> processedActions = actionProcessor.process(action, mergedContext);
            log.info("Finished processing action");
            reportingService.reportSuccessful(processedActions);
            return processedActions;
        } catch (Exception e) {
            log.warn("Action dropped", e);
            reportingService.reportFailure(action, e);
        } finally {
            MDC.remove("rootUri");
            MDC.remove("actionType");
        }
        return Collections.emptyList();
    }

    private String getActionType(Action action) {
        if (action instanceof DeleteAction) {
            return "delete";
        } else {
            return "store";
        }
    }

}

