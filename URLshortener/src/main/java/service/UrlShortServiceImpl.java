package service;

import dto.UrlRequestDto;
import dto.UrlShortDto;
import mapper.UrlMapper;
import model.UrlShortEntity;
import repository.UrlShortMapper;
import service.UrlShortService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UrlShortServiceImpl implements UrlShortService {
    private UrlShortMapper urlShortenerMapper;

    @Transactional
    public UrlShortDto newUrl(UrlRequestDto requestDto) {
        final String SHORT_URL_START = "http://localhost/";

        UrlShortEntity urlEntity;
        UrlShortDto urlDto;

        urlEntity = urlShortenerMapper.getUrlInfo(requestDto);

        if (urlEntity != null) {
            urlDto = UrlMapper.entityToDto(urlEntity);
            return urlDto;
        }

        String key = generateHash(requestDto.getUrl()).substring(0, 8);

        Integer keyCount = urlShortenerMapper.getKeyCount(key);
        if (keyCount > 0) {
            String longUrl = requestDto.getUrl() + UUID.randomUUID().toString();
            key = generateHash(longUrl).substring(0, 8);
        }

        String shortUrl = SHORT_URL_START + key;
        UrlShortDto dtoToSave = new UrlShortDto();
        dtoToSave.setKeyValue(key);
        dtoToSave.setLongUrl(requestDto.getUrl());
        dtoToSave.setShortUrl(shortUrl);
        urlShortenerMapper.insertUrlInfo(dtoToSave);

        return dtoToSave;
    }

    public UrlShortDto getLongUrl(String key) {
        UrlShortEntity entity;
        UrlShortDto dto = new UrlShortDto();
        entity = urlShortenerMapper.getLongUrl(key);

        if (entity == null) {
            return null;
        }

        dto.setLongUrl(entity.getLongUrl());

        return dto;
    }

    public Integer deleteUrl(String key) {
        return urlShortenerMapper.deleteUrl(key);
    }

    private String generateHash(String longUrl) {
        try {
            MessageDigest hash = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = hash.digest(longUrl.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash!", e);
        }
    }
}
