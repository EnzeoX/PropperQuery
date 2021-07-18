package com.github.service;

import com.github.entity.WaitingEntity;

import java.util.ArrayList;
import java.util.List;

public class WaitingService {

    private final List<WaitingEntity> waitingEntities = new ArrayList<>();

    public void saveInfo(WaitingEntity data) {
        this.waitingEntities.add(data);
    }

    public List<WaitingEntity> getInfo() {
        return this.waitingEntities;
    }
}
