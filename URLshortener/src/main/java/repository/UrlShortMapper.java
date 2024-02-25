package repository;

import dto.UrlRequestDto;
import dto.UrlShortDto;
import model.UrlShortEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UrlShortMapper {

    UrlShortEntity getUrlInfo (@Param("requestDto") UrlRequestDto requestDto);

    Integer getKeyCount (@Param("key") String key);

    void insertUrlInfo(@Param("dto")UrlShortDto dtoToSave);

    UrlShortEntity getLongUrl (@Param("key") String key);

    Integer deleteUrl (@Param("key") String key);

}
