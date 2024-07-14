package org.example.domain;

import lombok.ToString;

@ToString
public abstract class MetadaStoreAction extends Action {

    public MetadaStoreAction(String id) {
        super(id);
    }
}
