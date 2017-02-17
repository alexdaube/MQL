package infrastructure;


import domain.keywords.Keywords;

import java.util.Map;

public class KeywordDevDataFactory {

    private final String SITE = "SITE";
    private final String NAME = "NAME";
    private final String ADDRESS = "ADDRESS";
    private final String CITY = "CITY";
    private final String ZIPCODE = "ZIPCODE";
    private final String EQUIPEMENT = "EQUIPEMENT";
    private final String SERIAL_NO = "SERIAL_NO";
    private final String DATE_IN_SERVICE = "DATE_IN_SERVICE";
    private final String WARRANTY_END = "WARRANTY_END";

    public KeywordDevDataFactory() {

    }

    public Map<Keywords.Type, Keywords> createStubKeywords() {

        return null;
    }

}
