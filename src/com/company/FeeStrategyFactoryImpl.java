package com.company;

public class FeeStrategyFactoryImpl implements FeeStrategyFactory {

    @Override
    public FeeStrategy make(String feeName) {
        switch (feeName) {
            case "MIN_MAX":
                return new MinMax();
            case "SPECIAL_EVENT":
                return new SpecialEvent();
            case "LOST_TICKET":
                return new LostTicket();
        }
        return null;
    }
}
