package fr.ecopoint.service;

import fr.ecopoint.model.entities.Article;

public interface ArticleService {
    Article readFromCode(String code);
}
