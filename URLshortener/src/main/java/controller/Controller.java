package controller;

import dto.UrlRequestDto;
import dto.UrlShortDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UrlShortService;

import java.net.URI;
public class Controller {
    private UrlShortService urlShortService;

    @PostMapping(value = "/newUrl")
    public ResponseEntity<UrlShortDto> newUrl(@Valid @RequestBody (required = true) UrlRequestDto requestDto) {
        UrlShortDto responseDto = urlShortService.newUrl(requestDto);
        return new ResponseEntity<UrlShortDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{key}")
    public ResponseEntity<Object> redirectToLongUrl (@PathVariable String key) {
        UrlShortDto dto = urlShortService.getLongUrl(key);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(dto.getLongUrl())).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found");
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Object> deleteUrl (@PathVariable String key) {
        Integer deletedCount = urlShortService.deleteUrl(key);

        if (deletedCount > 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no URL for the input key");
        }
    }
}
