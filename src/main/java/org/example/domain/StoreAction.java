package org.example.domain;

public sealed interface StoreAction extends Action permits ChmStoreAction, ChoStoreAction, DatasetStoreAction, ManifestationStoreAction, MetadaStoreAction, SkosStoreAction {
    String file();

    String mimeType();

}

