package fr.ecopoint.api;

import fr.ecopoint.api.excepion.ApiException;
import fr.ecopoint.model.entities.Article;
import fr.ecopoint.model.entities.ArticleUser;
import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.ArticleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryArticle;
import fr.ecopoint.model.factory.FactoryArticleUser;
import fr.ecopoint.model.factory.FactoryPointCollect;
import fr.ecopoint.service.ArticleService;
import fr.ecopoint.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController("/api")
public final class AccessByAPI {
    private static final Logger LOGGER = LogManager.getLogger(AccessByAPI.class);

    private final UserService userService;
    private final ArticleService articleService;

    private static final String KEY_COOKIE = "moncookieperso";

    public AccessByAPI(final UserService userService, final ArticleService articleService) {
        LOGGER.debug("UserController()");
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping(value = "/authentification",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> authentification(@RequestParam(value = "pseudo", defaultValue = "") final String pseudo, @RequestParam(value = "password", defaultValue = "") final String password, final HttpSession session) {
        try {
            if(pseudo == null || password == null){
                throw new ApiException("Identifiant non valide.");
            }
            final User u = this.userService.findByPseudoAndPassword(pseudo,password);
            if(u==null){
                throw new ApiException("Identifiant non valide.");
            }
            session.setAttribute("key", KEY_COOKIE + u.getId());
            return new ResponseEntity<>(u, HttpStatus.FOUND);
        } catch (final ApiException | UserException e) {
            final String  str = "user non trouvé : " + e.getMessage();
            LOGGER.debug(str);
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> seDeconnecter(final HttpSession session) {
        LOGGER.debug("seDeconnecter()");
        final String s =(String) session.getAttribute("key");
        if(s!= null) {
            session.removeAttribute("key");
            return new ResponseEntity<>("", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addArticle")
    public ResponseEntity<Object> addArticle(@RequestParam(value = "idUser", defaultValue = "") final long idUser, @RequestParam(value = "codeArticle", defaultValue = "") final String codeArticle, @RequestParam(value = "quantiterArticle", defaultValue = "0") final int quantiterArticle, final HttpSession session) {
        final String s =(String) session.getAttribute("key");
        if(s != null && s.equals(KEY_COOKIE + idUser)) {
            final User u = this.userService.readFromKey(idUser);
            final Article article = this.articleService.readFromCode(codeArticle);

            if (article != null) {
                PointCollect pointCollect = FactoryPointCollect.getPointCollect(quantiterArticle);
                ArticleUser articleUser = FactoryArticleUser.getArticleUser(article, pointCollect);
                u.ajouterArticleListe(articleUser);
                this.userService.save(u, false);
                return new ResponseEntity<>("",HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/findArticle")
    public ResponseEntity<Object> rechercherArticle(@RequestParam(value = "codeArticle", defaultValue = "") final String codeArticle) {
            final Article article = this.articleService.readFromCode(codeArticle);
            if (article != null) {
                return new ResponseEntity<>(article,HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addArticleNew")
    public ResponseEntity<Object> addArticleNew(@RequestParam(value = "idUser", defaultValue = "") final long idUser, @RequestParam(value = "codeArticle", defaultValue = "") final String codeArticle, final int quantiterArticle, final int prix, final String nom, final HttpSession session) {
        final String s =(String) session.getAttribute("key");
        if(s != null && s.equals(KEY_COOKIE + idUser)) {
            final User u = this.userService.readFromKey(idUser);
            final Article article;
            try{
                article = FactoryArticle.getArticle(nom,codeArticle,prix);
            }catch (ArticleException e){
                return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
            }

            PointCollect pointCollect = FactoryPointCollect.getPointCollect(quantiterArticle);
            ArticleUser articleUser = FactoryArticleUser.getArticleUser(article, pointCollect);
            u.ajouterArticleListe(articleUser);
            this.userService.save(u, false);
            return new ResponseEntity<>("",HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
    }
}
