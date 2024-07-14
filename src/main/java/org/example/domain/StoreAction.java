package org.example.domain;

import lombok.ToString;

import java.io.File;

@ToString
public abstract sealed class StoreAction extends Action permits ChmStoreAction, ChoStoreAction, DatasetStoreAction, ManifestationStoreAction, MetadaStoreAction {
    File file;
    String mimeType;

    public StoreAction(String id) {
        super(id);
    }
}

