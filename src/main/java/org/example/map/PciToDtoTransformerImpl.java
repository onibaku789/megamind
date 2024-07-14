package org.example.map;

import org.example.api.Manifest;
import org.example.domain.Action;

import java.util.List;

public class PciToDtoTransformerImpl implements PciToDtoTransformer {
    @Override
    public List<Action> transform(Manifest zip) {
        return List.of();
    }
}
