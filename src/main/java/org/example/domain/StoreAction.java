package org.example.domain;

import java.io.File;

public sealed interface StoreAction extends Action permits ChmStoreAction, ChoStoreAction, DatasetStoreAction, ManifestationStoreAction, MetadaStoreAction, SkosStoreAction {
    String file();

    String mimeType();

}

