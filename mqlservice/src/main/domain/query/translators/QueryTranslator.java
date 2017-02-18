package domain.query.translators;

import domain.query.Query;

public interface QueryTranslator {
    String translate(Query query);
}
