package org.example.map;

import org.example.api.Manifest;
import org.example.domain.Action;

import java.util.List;

public class DtoToC2TransformerImpl implements DtoToPciTransformer {
    @Override
    public Manifest transform(List<Action> storeActionList) {
        return (Manifest) new Object();
    }
}
