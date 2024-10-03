package me.chanwoong.springbootdeveloper.repository;

import me.chanwoong.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
