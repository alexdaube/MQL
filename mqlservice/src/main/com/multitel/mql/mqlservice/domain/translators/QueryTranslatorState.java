package com.multitel.mql.mqlservice.domain.translators;

import com.multitel.mql.mqlservice.domain.StringQuery;

public interface QueryTranslatorState {
    boolean translate(StringQuery stringQuery);
}
