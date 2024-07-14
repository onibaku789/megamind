package org.example.domain;


public sealed interface Action permits DeleteAction, StoreAction {
    String id();

    String owlVersionInfo();

    String aspect();

}
