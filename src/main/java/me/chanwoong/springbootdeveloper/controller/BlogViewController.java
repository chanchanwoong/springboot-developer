package me.chanwoong.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.chanwoong.springbootdeveloper.domain.Article;
import me.chanwoong.springbootdeveloper.dto.ArticleListViewResponse;
import me.chanwoong.springbootdeveloper.dto.ArticleViewResponse;
import me.chanwoong.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        // view로 사용되는 html 명을 작성
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        // 게시글 생성과 수정을 id 기준으로 없으면 생성 화면, 있으면 수정 화면으로 쓸려고 한다.
        // 각 페이지를 안 만들고 재활용 느낌

        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
