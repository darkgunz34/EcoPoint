package fr.ecopoint.api;

import fr.ecopoint.api.excepion.ApiException;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public final class Authentification {
    private static final Logger LOGGER = LogManager.getLogger(Authentification.class);

    private final UserService userService;

    private static final String KEY_COOKIE = "moncookieperso";

    public Authentification(final UserService userService) {
        LOGGER.debug("UserController()");
        this.userService = userService;
    }

    @GetMapping(value = "/authentification",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> authentification(@RequestParam(value = "pseudo", defaultValue = "") final String pseudo, @RequestParam(value = "password", defaultValue = "") final String password, final HttpSession session) {
        try {
            UtilsEntity.champNonVideUser(pseudo);
            UtilsEntity.champNonVideUser(password);
            final User u = this.userService.findByMailAndPassword(pseudo,password);
            if(u==null){
                throw new ApiException("User inexistant en bdd.");
            }
            LOGGER.debug("user trouvé");
            session.setAttribute("key", KEY_COOKIE + u.getId());
            return new ResponseEntity<>(u, HttpStatus.FOUND);
        } catch (final ApiException e) {
            final String  str = "user non trouvé : " + e.getMessage();
            LOGGER.debug(str);
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        }
    }
}
