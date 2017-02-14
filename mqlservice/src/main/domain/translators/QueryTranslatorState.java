package domain.translators;

import domain.StringQuery;

public interface QueryTranslatorState {
    StateStatus translate(StringQuery stringQuery);
}
