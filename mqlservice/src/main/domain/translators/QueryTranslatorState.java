package domain.translators;

import domain.StringQuery;

public interface QueryTranslatorState {
    boolean translate(StringQuery stringQuery);
}
