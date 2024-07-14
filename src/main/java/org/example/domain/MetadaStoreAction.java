package org.example.domain;

import lombok.ToString;

@ToString
public abstract sealed class MetadaStoreAction extends StoreAction permits ExpressionMetadaStoreAction, WorkMetadaStoreAction {

    public MetadaStoreAction(String id) {
        super(id);
    }
}
