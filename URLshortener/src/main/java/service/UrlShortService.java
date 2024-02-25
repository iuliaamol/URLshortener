package service;

import dto.UrlRequestDto;
import dto.UrlShortDto;

public interface UrlShortService {

    UrlShortDto newUrl (UrlRequestDto requestDto);

    UrlShortDto getLongUrl (String key);

    Integer deleteUrl (String key);

}