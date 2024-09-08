package com.url_shortener.Service;
import com.url_shortener.Entity.Url;
import com.url_shortener.Repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String generateShortUrl(String originalUrl) {
        Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);

        if(existingUrl.isPresent()) {
            return existingUrl.get().getShortUrl();
        }

        String shortUrl = generateRandomString();

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setCreationDate(LocalDateTime.now());
        urlRepository.save(url);

        return shortUrl;
    }

    private String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public String getOriginalUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);

        if(url.isPresent()) {
            return url.get().getOriginalUrl();
        } else {
            throw new RuntimeException("Short URL not found");
        }
    }

}
