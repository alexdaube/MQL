package com.multitel.mql.mqlservice.infrastructure;


import com.multitel.mql.mqlservice.domain.IdGenerator;
import com.multitel.mql.mqlservice.domain.Keyword;
import com.multitel.mql.mqlservice.domain.KeywordFactory;

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


//    CREATE TABLE dbo.Products
//   (ProductID int PRIMARY KEY NOT NULL,
//    ProductName varchar(25) NOT NULL,
//    Price money NULL,
//    ProductDescription text NULL)
//    GO


    public KeywordDevDataFactory() {

    }

    public List<Keyword> createStubKeywords() {
        List<Keyword> keywords = new ArrayList<>();

        IdGenerator idGenerator = new IdGenerator();
        KeywordFactory keywordFactory = new KeywordFactory(idGenerator);

        Keyword site = keywordFactory.createParentKeyword(SITE);
        Keyword equipement = keywordFactory.createParentKeyword(EQUIPEMENT);


        keywords.add(site);
        keywords.add(equipement);

        return keywords;
    }

}
