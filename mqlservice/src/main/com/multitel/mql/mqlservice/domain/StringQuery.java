package com.multitel.mql.mqlservice.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringQuery {
    private final Pattern stripPattern;
    private String query;

    public StringQuery(String query) {
        this.stripPattern = Pattern.compile("^[^\\w\"-]+");
        this.query = query;
    }

    public void strip() {
        Matcher matcher = stripPattern.matcher(query);
        if (matcher.find()) {
            this.query = matcher.replaceFirst("");
        }
    }

    public Matcher findMatches(Pattern pattern) {
        strip();
        return pattern.matcher(query);
    }

    public void removeFirstMatch(Pattern pattern) {
        strip();
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            this.query = matcher.replaceFirst("");
        }
    }

    public boolean isEqualTo(String other) {
        return query.equals(other);
    }

    public boolean isEmpty() {
        return query.isEmpty();
    }
}
