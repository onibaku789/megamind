package org.example.domain;


public sealed interface DeleteAction extends Action permits SimpleDeleteAction {
    String type();
}