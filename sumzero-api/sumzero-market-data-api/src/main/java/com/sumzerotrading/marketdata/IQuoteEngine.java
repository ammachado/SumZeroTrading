/*
 * MIT License
 *
 * Copyright (c) 2015 Rob Terpilowski
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.sumzerotrading.marketdata;

import java.util.Date;
import java.util.Properties;

import com.sumzerotrading.data.Ticker;

/**
 * The QuoteEngine is used to subscribe to Level1 and Level2 quotes
 *
 * @author RobTerpilowski
 */
public interface IQuoteEngine {

    /**
     * Properties related to Level 1 and level 2 quotes
     */
    enum Property {
        BID, ASK, MIDPOINT, TRADES
    }

    /**
     *
     */
    enum Side {
        BID, ASK
    }

    /**
     * Subscribe to level 1 market data
     *
     * @param ticker The ticker to create the subscription for
     * @param listener The listener which will be invoked when quotes for the specified ticker arrive.
     */
    void subscribeLevel1(Ticker ticker, Level1QuoteListener listener);

    /**
     * Unsubscribe from Level 1 market data
     *
     * @param ticker The ticker to unsubscribe from
     * @param listener The listener that previously had an active subscription.
     */
    void unsubscribeLevel1(Ticker ticker, Level1QuoteListener listener);

    /**
     * Start the quote engine.
     */
    void startEngine();

    /**
     * Start the quote engine with the specified properties
     *
     * @param props The properties needed to start the quote engine.
     */
    void startEngine(Properties props);

    /**
     * Stops the quote engine
     */
    void stopEngine();

    /**
     * Subscribe to MarketDepth (Level 2) quotes
     *
     * @param ticker The ticker to subscribe to
     * @param listener The listener which will receive the market depth quote events
     */
    void subscribeMarketDepth(Ticker ticker, Level2QuoteListener listener);

    /**
     * Unsubscribe from Market Depth quotes
     *
     * @param ticker The ticker that to unsubscribe from
     * @param listener The listener that is holding the current subscription.
     */
    void unsubscribeMarketDepth(Ticker ticker, Level2QuoteListener listener);

    /**
     * Gets the current time from the QuoteEngine
     *
     * @return The current time from the QuoteEngine
     */
    Date getServerTime();

    /**
     * Adds a listener to listen for error events from the QuoteEngine
     *
     * @param listener The errorListener to add to the quote engine.
     */
    void addErrorListener(ErrorListener listener);

    /**
     * Removes the specified error listener from the quote engine
     *
     * @param listener The listener to remove from the quote engine
     */
    void removeErrorListener(ErrorListener listener);

    /**
     * @return true if the quote engine is started.
     */
    boolean started();

    /**
     * @return true if the quote engine is connected to its data source
     */
    boolean isConnected();

    /**
     * Fires the level 1 quote
     *
     * @param quote The level 1 quote to fire.
     */
    void fireLevel1Quote(ILevel1Quote quote);

    /**
     * Fires a Level 2 quote
     *
     * @param quote The market depth quote to fire.
     */
    void fireMarketDepthQuote(ILevel2Quote quote);

    /**
     * The error event to fire
     *
     * @param error The quote error to fire.
     */
    void fireErrorEvent(QuoteError error);

    /**
     * Set to true if ok to use delayed data.
     *
     * @param useDelayed true if ok to use delayed rather than real-time data
     */
    void useDelayedData(boolean useDelayed);
}
