package domain.interpreters.values;

import domain.querybuilder.QueryBuilder;
import domain.Query;
import domain.interpreters.Interpreter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateInterpreter implements Interpreter {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    static final Pattern DATE_PATTERN = Pattern.compile("^((\\d{4})\\W(\\d{2})\\W(\\d{2}))");

    @Override
    public boolean interpret(Query query, QueryBuilder queryBuilder) {
        Matcher matches = query.findMatches(DATE_PATTERN);
        if (matches.find()) {
            query.removeFirstMatch(DATE_PATTERN);
            try {
                queryBuilder.withDate(parseDate(matches));
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    private Date parseDate(Matcher matches) throws ParseException {
        String year = matches.group(2);
        String month = matches.group(3);
        String day = matches.group(4);
        return new Date(DATE_FORMAT.parse(year+month+day).getTime());
    }
}
