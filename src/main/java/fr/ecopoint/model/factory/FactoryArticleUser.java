package fr.ecopoint.model.factory;

import fr.ecopoint.model.entities.Article;
import fr.ecopoint.model.entities.ArticleUser;
import fr.ecopoint.model.entities.PointCollect;

import java.time.LocalDate;

public final class FactoryArticleUser {
    private FactoryArticleUser(){

    }

    public static ArticleUser getArticleUser(Article article,PointCollect point){
        LocalDate date = LocalDate.now();
        return new ArticleUser(date,article,point);
    }
}
