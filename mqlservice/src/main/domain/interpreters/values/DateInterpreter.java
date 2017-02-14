package domain.interpreters.values;

import domain.QueryBuilder;
import domain.StringQuery;
import domain.interpreters.Interpreter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateInterpreter implements Interpreter {
    private static final Pattern DATE_PATTERN = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})( \\d{2}:\\d{2}:\\d{2})?(.\\d{3})?");
    private static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final SimpleDateFormat MEDIUM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SMALL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final Interpreter next;

    public DateInterpreter(Interpreter next) {
        this.next = next;
    }

    @Override
    public boolean interpret(StringQuery query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(DATE_PATTERN);
        if (matches.find()) {
            String match = matches.group();
            query.removeFirstMatch(DATE_PATTERN);
            try {
                queryBuilder.withDate(parseDate(match));
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
        return next.interpret(query, queryBuilder);
    }

    private Date parseDate(String stringDate) throws ParseException {
        try {
            return new Date(FULL_DATE_FORMAT.parse(stringDate).getTime());
        } catch (ParseException ignored) {}
        try {
            return new Date(MEDIUM_DATE_FORMAT.parse(stringDate).getTime());
        } catch (ParseException ignored) {}
        return new Date(SMALL_DATE_FORMAT.parse(stringDate).getTime());
    }
}
