package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortEntity {
    Integer urlId;
    String keyValue;
    String longUrl;
    String shortUrl;
}