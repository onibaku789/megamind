package org.example.map;

import org.example.Manifest;
import org.example.domain.Action;

import java.util.List;

public interface DtoToPciTransformer {
    Manifest transform(List<Action> storeActionList);
}
