package com.company;

public interface FeeStrategyFactory {
    FeeStrategy make(String parkingFeeName);
}
