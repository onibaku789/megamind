package org.example.domain;

import lombok.ToString;

@ToString
public abstract class DeleteAction extends Action {

    String type;

    public DeleteAction(String id) {
        super(id);
    }
}