package org.example.domain;

import lombok.ToString;

@ToString
public sealed class DatasetStoreAction extends StoreAction permits SkosStoreAction {


    public DatasetStoreAction(String id) {
        super(id);
    }
}
