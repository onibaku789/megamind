package org.example.domain;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public non-sealed interface ExpressionMetadaStoreAction extends MetadaStoreAction {
    List<ManifestationStoreAction> manifests();


}
