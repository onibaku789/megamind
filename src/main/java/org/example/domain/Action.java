package org.example.domain;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public sealed abstract class Action permits DeleteAction, StoreAction {
    protected final String id;
    protected String owlVersionInfo;
    protected String aspect;

}
