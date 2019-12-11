package com.company;

public class FeeStrategyFactoryImpl implements FeeStrategyFactory {

    /**
     * The factory implementation for fee strategies
     * Min Max, Special Event, Lost ticket
     * @param feeName name of the strategies
     * @return a fee strategy
     */
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
