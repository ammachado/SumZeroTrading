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

import com.sumzerotrading.data.StockTicker;
import com.sumzerotrading.data.SumZeroException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author RobTerpilowski
 */
public class Level1QuoteTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLevel1Quote() {
        BigDecimal bid = new BigDecimal(2.5);
        BigDecimal ask = new BigDecimal(3.5);

        StockTicker ticker = new StockTicker("ABC");
        Map<QuoteType, BigDecimal> map = new HashMap<>();
        map.put(QuoteType.BID, bid);
        map.put(QuoteType.ASK, ask);

        ZonedDateTime now = ZonedDateTime.now();

        Level1Quote quote = new Level1Quote(ticker, now, map);

        List<QuoteType> returnedTypes = Arrays.asList(quote.getTypes());
        assertEquals(2, returnedTypes.size());
        assertTrue(returnedTypes.contains(QuoteType.BID));
        assertTrue(returnedTypes.contains(QuoteType.ASK));

        assertTrue(quote.containsType(QuoteType.BID));
        assertTrue(quote.containsType(QuoteType.ASK));
        assertFalse(quote.containsType(QuoteType.OPEN));

        assertEquals(bid, quote.getValue(QuoteType.BID));

        try {
            quote.getValue(QuoteType.OPEN);
            fail();
        } catch (SumZeroException ex) {
            //this should happen
        }
    }
}
