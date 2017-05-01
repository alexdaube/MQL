package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ResultTest {

    private Result result;
    @Mock
    private List<Map<String, Object>> data;

    @Before
    public void setUp() {
        result = new Result(data);
    }

    @Test
    public void givenDataAtInit_whenGetElements_thenReturnData() {
        assertThat(result.getElements(), is(equalTo(data)));
    }

}