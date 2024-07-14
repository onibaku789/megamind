package org.example.domain;

import lombok.ToString;

import java.util.List;
@ToString
public final class ExpressionMetadaStoreAction extends MetadaStoreAction {
    List<ManifestationStoreAction> manifests;

    public ExpressionMetadaStoreAction(String id) {
        super(id);
    }
}
