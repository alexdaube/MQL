package domain.keywords;

import domain.keyword.KeywordConfig;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KeywordTest {

    private final String SITE = "SITE";
    private final String CITY = "CITY";
    private final String EQUIPEMENT = "EQUIPEMENT";
    @Mock
    KeywordConfig mockedKeyword;
    @Mock
    KeywordConfig anotherMockedKeyword;

}