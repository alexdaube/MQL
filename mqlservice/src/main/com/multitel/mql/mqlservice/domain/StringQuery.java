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

    public boolean isEqualTo(String other) {
        return query.equals(other);
    }

    public String extractFirst(Pattern pattern) {
        strip();
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            String match = matcher.group();
            this.query = matcher.replaceFirst("");
            return match;
        }
        throw new InvalidQueryException("No pattern found...");
    }

    public boolean extractFirst(String word) {
        strip();
        int index = query.indexOf(word);
        if (index == 0) {
            this.query = query.substring(word.length());
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return query.isEmpty();
    }
}
