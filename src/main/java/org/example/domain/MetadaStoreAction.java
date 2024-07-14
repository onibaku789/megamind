package org.example.domain;

public sealed interface MetadaStoreAction extends StoreAction permits ExpressionMetadaStoreAction, WorkMetadaStoreAction {


}
