package domain.keywords;

public interface Keywords {

    boolean contains(String keyword);

    String parentOf(String keyword);
}
