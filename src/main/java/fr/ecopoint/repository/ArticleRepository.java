package fr.ecopoint.repository;

import fr.ecopoint.model.entities.Article;
import fr.ecopoint.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long > {

    Article findByCode(String code);
}