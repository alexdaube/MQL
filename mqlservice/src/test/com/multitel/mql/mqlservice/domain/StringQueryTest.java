package com.multitel.mql.mqlservice.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringQueryTest {
    private static final String INVALID_TERM = "QUERY2";
    private static final String FIRST_TERM = "QUERY1";
    private static final String SECOND_TERM = "IM2";
    private static final String QUERY = FIRST_TERM + " " + SECOND_TERM;
    private StringQuery stringQuery;


    @Before
    public void setUp() throws Exception {
        stringQuery = new StringQuery(QUERY);
    }

    @Test
    public void givenTheFirstTerm_whenExtractingFirst_thenReturnTrue() throws Exception {
        boolean returnValue = stringQuery.extractFirst(FIRST_TERM);
        assertTrue(returnValue);
    }

    @Test
    public void givenAnInvalidString_whenExtractingFirst_thenReturnFalse() throws Exception {
        boolean returnValue = stringQuery.extractFirst(INVALID_TERM);
        assertFalse(returnValue);
    }

    @Test
    public void givenAQueryWithOneWordAndAValidFirstTerm_whenExtractingFirst_thenReturnTrue() throws Exception {
        stringQuery = new StringQuery(FIRST_TERM);
        boolean returnValue = stringQuery.extractFirst(FIRST_TERM);
        assertTrue(returnValue);
    }

    @Test
    public void givenACorrectString_whenStripping_thenTheStringShouldRemainUntouched() throws Exception {
        stringQuery.strip();
        assertTrue(stringQuery.isEqualTo(QUERY));
    }

    @Test
    public void givenAStringBeginningWithBlanks_whenStripping_thenTheStringShouldBeStripped() throws Exception {
        stringQuery = new StringQuery("     " + QUERY);
        stringQuery.strip();
        assertTrue(stringQuery.isEqualTo(QUERY));
    }
    
    @Test
    public void givenARegexPattern_whenExtractingFirst_thenTheFirstMatchShouldBeExtracted() throws Exception {
        Pattern pattern = Pattern.compile("^"+FIRST_TERM);
        String match = stringQuery.extractFirst(pattern);
        assertThat(match, is(equalTo(FIRST_TERM)));
    }

    @Test
    public void givenARegexPattern_whenExtractingFirst_thenTHeFirstMatchShouldBeRemovedFromTheQuery() throws Exception {
        Pattern pattern = Pattern.compile("^"+FIRST_TERM);
        stringQuery.extractFirst(pattern);
        stringQuery.strip();
        assertTrue(stringQuery.isEqualTo(SECOND_TERM));
    }
}