package mapper;

import dto.UrlShortDto;
import model.UrlShortEntity;

public class UrlMapper {

    public static UrlShortDto entityToDto (UrlShortEntity entity) {
        UrlShortDto dto = new UrlShortDto();
        dto.setKeyValue(entity.getKeyValue());
        dto.setLongUrl(entity.getLongUrl());
        dto.setShortUrl(entity.getShortUrl());
        return dto;
    }

}
