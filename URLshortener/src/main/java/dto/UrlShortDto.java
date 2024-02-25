package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortDto {
    String keyValue;
    String longUrl;
    String shortUrl;
}
