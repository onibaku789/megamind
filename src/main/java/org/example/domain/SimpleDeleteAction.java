package org.example.domain;

import lombok.ToString;

@ToString(callSuper = true)
public final class SimpleDeleteAction extends DeleteAction {

    public SimpleDeleteAction(String id) {
        super(id);
    }
}
