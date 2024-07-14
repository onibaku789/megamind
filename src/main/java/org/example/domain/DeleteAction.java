package org.example.domain;

import lombok.ToString;

@ToString
public abstract sealed class DeleteAction extends Action permits SimpleDeleteAction {

    String type;

    public DeleteAction(String id) {
        super(id);
    }
}