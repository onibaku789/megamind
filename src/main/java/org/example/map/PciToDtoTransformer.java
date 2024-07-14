package org.example.map;

import org.example.Manifest;
import org.example.domain.Action;

import java.util.List;

public interface PciToDtoTransformer {
    List<Action> transform(Manifest zip);
}
