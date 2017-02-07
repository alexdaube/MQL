package com.multitel.mql.mqlservice.domain.keyword;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeywordTest {

    private final String SITE = "SITE";
    private final String NAME = "NAME";
    private final String ADDRESS = "ADDRESS";
    private final String CITY = "CITY";
    private final String ZIPCODE = "ZIPCODE";
    private final String EQUIPEMENT = "EQUIPEMENT";
    private final String SERIAL_NO = "SERIAL_NO";
    private final String DATE_IN_SERVICE = "DATE_IN_SERVICE";
    private final String WARRANTY_END = "WARRANTY_END";

    private KeywordFactory keywordFactory;

    @Mock
    Keyword mockedKeyword;

    @Mock
    Keyword anotherMockedKeyword;

    @Before
    public void setUp() throws Exception {
        keywordFactory = new KeywordFactory();
    }

    @Test
    public void whenCreationEntityKeyword_thenKeywordNotASubset() {
        Keyword site = keywordFactory.createEntityKeyword(SITE);
        assertFalse(site.isSubsetOf(mockedKeyword));
    }

    @Test
    public void whenCreatingAttributeKeyword_thenKeywordHasParent() {
        when(mockedKeyword.getKeyword()).thenReturn(SITE);
        Keyword city = keywordFactory.createAttributeKeyword(mockedKeyword, CITY);
        assertTrue(city.isSubsetOf(mockedKeyword));
    }

    @Test
    public void attributeKeyword_canValidateHisParent() {
        when(mockedKeyword.getKeyword()).thenReturn(SITE);
        when(anotherMockedKeyword.getKeyword()).thenReturn(EQUIPEMENT);

        Keyword city = keywordFactory.createAttributeKeyword(mockedKeyword, CITY);

        assertFalse(city.isSubsetOf(anotherMockedKeyword));
    }

}