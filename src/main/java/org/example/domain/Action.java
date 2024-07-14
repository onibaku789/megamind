package org.example.domain;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class Action {
    protected final String id;
    protected String owlVersionInfo;
    protected String aspect;

}
