package me.kacper.policeThief;

import lombok.Getter;

@Getter
public enum ModeType {

    ONE("one"), TWO("two"), THREE("three"), FOUR("four");

    final String configType;

    ModeType(String configType) {
        this.configType = configType;
    }
}
