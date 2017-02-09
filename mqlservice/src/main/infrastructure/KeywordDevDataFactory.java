package infrastructure;


import domain.keyword.KeywordFactory;
import domain.keyword.Keyword;

import java.util.ArrayList;
import java.util.List;

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

    public List<Keyword> createStubKeywords() {
        List<Keyword> keywords = new ArrayList<>();

        KeywordFactory keywordFactory = new KeywordFactory();

        Keyword site = keywordFactory.createEntityKeyword(SITE);
        Keyword equipement = keywordFactory.createEntityKeyword(EQUIPEMENT);

        Keyword name = keywordFactory.createAttributeKeyword(site, NAME);
        Keyword city = keywordFactory.createAttributeKeyword(site, CITY);
        Keyword address = keywordFactory.createAttributeKeyword(site, ADDRESS);
        Keyword zipCode = keywordFactory.createAttributeKeyword(site, ZIPCODE);
        Keyword serialNumber = keywordFactory.createAttributeKeyword(equipement, SERIAL_NO);
        Keyword dateService = keywordFactory.createAttributeKeyword(equipement, DATE_IN_SERVICE);
        Keyword warrantyEnd = keywordFactory.createAttributeKeyword(equipement, WARRANTY_END);

        keywords.add(site);
        keywords.add(name);
        keywords.add(city);
        keywords.add(address);
        keywords.add(zipCode);
        keywords.add(equipement);
        keywords.add(serialNumber);
        keywords.add(dateService);
        keywords.add(warrantyEnd);

        return keywords;
    }

}
