package com.url_shortener.Repository;
import com.url_shortener.Entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);
    Optional<Url> findByOriginalUrl(String originalUrl);

}
