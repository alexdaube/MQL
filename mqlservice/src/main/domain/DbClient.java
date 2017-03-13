package domain;

import services.query.AttributeDto;

import java.util.List;

public interface DbClient {
    List<List<AttributeDto>> execute(String query);
}
