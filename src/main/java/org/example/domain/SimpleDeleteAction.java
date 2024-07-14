package org.example.domain;

import lombok.ToString;

@ToString(callSuper = true)
public class SimpleDeleteAction extends Action {

    public SimpleDeleteAction(String id) {
        super(id);
    }
}
