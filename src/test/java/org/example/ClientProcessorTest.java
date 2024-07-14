package org.example;

import org.example.client.ClientActionProcessor;
import org.example.domain.*;
import org.example.reporting.DummyReportingService;
import org.example.reporting.ReportingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientProcessorTest {

    @Spy
    private ClientActionProcessor actionProcessor = new ClientActionProcessor();
    @Spy
    private ReportingService reportingService = new DummyReportingService();

    @InjectMocks
    private Processor processor;

    private List<Action> actions;
    private BasicContext basicContext;

    @BeforeEach
    void setUp() {
        actions = List.of(
                new SimpleDeleteAction("DELETE 1"),
                new ExpressionMetadaStoreAction("STORE EXPRESSION 1"),
                new SimpleDeleteAction("DELETE EXPRESSION 2"),
                new ChoStoreAction("STORE CHO 1"),
                new SimpleDeleteAction("DELETE WORK 1")
        );

        basicContext = new BasicContext("Some additional info");
    }

    @Test
    void testProcessAll() throws Exception {
        List<Action> processedActions = processor.processAll(actions, basicContext);

//        assertThat(processedActions)
//                .hasSize(2)
//                .extracting("id")
//                .containsExactly("1", "2");

        then(actionProcessor).should(atMost(actions.size())).process(any(Action.class), any(MergedContext.class));
    }
}
