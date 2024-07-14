package org.example.domain;

import lombok.ToString;

import java.io.File;

@ToString
public abstract class StoreAction extends Action {
    File file;
    String mimeType;

    public StoreAction(String id) {
        super(id);
    }
}

