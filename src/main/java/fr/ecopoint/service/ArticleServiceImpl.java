package fr.ecopoint.service;

import fr.ecopoint.model.entities.Article;
import fr.ecopoint.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements  ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article readFromCode(String code) {
        return this.articleRepository.findByCode(code);
    }
}
