package fr.ecopoint.model.factory;

import fr.ecopoint.model.entities.Article;
import fr.ecopoint.model.exception.ArticleException;
import fr.ecopoint.web.tools.Utils;

public class FactoryArticle {
    private FactoryArticle(){
    }

    public static Article getArticle(String nomArticle, String code, double prixApproximatif) throws ArticleException{
        champNonValide(nomArticle);
        champNonValide(code);
        return new Article(nomArticle,code,prixApproximatif);
    }

    private static void champNonValide(final String chaine) throws ArticleException{
        if(chaine.isEmpty()){
            throw new ArticleException("champ vide");
        }
    }
}
