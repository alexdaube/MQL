package configuration.keywords;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EntityMapTest {

    private EntityMap entityMap;
    private Set<GeneralKeyword> attributes;
    @Mock
    GeneralKeyword mockedGeneralKeyword;
    @Mock
    EntityKeyword mockedEntityKeyword;

    @Before
    public void setUp() {
        entityMap = new EntityMap();
        attributes = new HashSet<>();
        attributes.add(mockedGeneralKeyword);
        when(mockedEntityKeyword.getAttributes()).thenReturn(attributes);
    }

    @Test
    public void whenAddingKeywordToEntityMap_thenAttributesAreAccessedAndMapped() {
        entityMap.addEntity(mockedEntityKeyword);
        verify(mockedEntityKeyword, times(1)).getAttributes();
    }

    @Test
    public void whenAddingGeneralKeywordToEntity_thenEntityMapCanRetrieveGeneralKeywordWithEntityKeyword() {
        mockedEntityKeyword.setAttributes(attributes);
        entityMap.addEntity(mockedEntityKeyword);
        Set<GeneralKeyword> keywords = entityMap.getKeywordsFromEntity(mockedEntityKeyword);
        assertTrue(keywords.contains(mockedGeneralKeyword));
    }

}