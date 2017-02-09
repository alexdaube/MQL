package com.multitel.mql.mqlservice.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StringQueryTest {
    private static final String FIRST_TERM = "QUERY1";
    private static final String SECOND_TERM = "IM2";
    private static final String QUERY = FIRST_TERM + " " + SECOND_TERM;
    private static final Pattern PATTERN = Pattern.compile("^"+FIRST_TERM);
    private StringQuery stringQuery;


    @Before
    public void setUp() throws Exception {
        stringQuery = new StringQuery(QUERY);
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
    public void givenAPattern_whenRemovingFirstMatch_thenTHeFirstMatchShouldBeRemovedFromTheQuery() throws Exception {
        stringQuery.removeFirstMatch(PATTERN);
        stringQuery.strip();
        assertTrue(stringQuery.isEqualTo(SECOND_TERM));
    }

    @Test
    public void givenAPattern_whenFindingMatches_thenReturnMatches() throws Exception {
        Matcher matches = stringQuery.findMatches(PATTERN);
        assertTrue(matches.find());
        assertThat(matches.group(), is(equalTo(FIRST_TERM)));
    }
}