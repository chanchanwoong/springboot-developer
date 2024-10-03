package me.chanwoong.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.chanwoong.springbootdeveloper.domain.Article;
import me.chanwoong.springbootdeveloper.dto.AddArticleRequest;
import me.chanwoong.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }
}
