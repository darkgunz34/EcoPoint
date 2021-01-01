package fr.ecopoint.model;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MessageException;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryRole;
import fr.ecopoint.model.factory.FactoryUser;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    private static final String VALIDE_MAIL = "stephan.parichon@epsi.fr";
    private static final String VALIDE_ADRESSE = "Montpellier";
    private static final String VALIDE_MOT_DE_PASSE = "monMotDePasse";
    private static final String VALIDE_MOT_DE_PASSE_2 = "monMotDePasse";
    private static final String VALIDE_NOM = "Parichon";
    private static final String VALIDE_PRENOM = "Stéphan";
    private static final String VALIDE_TELEPHONE = "0645606439";

    private User user;
    private UserRegistrationDto userRegistrationDto;
    private Role role;

    @BeforeEach
    void init(){
        this.userRegistrationDto = new UserRegistrationDto();
        this.userRegistrationDto.setMail(VALIDE_MAIL);
        this.userRegistrationDto.setAdresse(VALIDE_ADRESSE);
        this.userRegistrationDto.setMotDePasse(VALIDE_MOT_DE_PASSE);
        this.userRegistrationDto.setMotDePasse2(VALIDE_MOT_DE_PASSE_2);
        this.userRegistrationDto.setNom(VALIDE_NOM);
        this.userRegistrationDto.setPrenom(VALIDE_PRENOM);
        this.userRegistrationDto.setTelephone(VALIDE_TELEPHONE);
        this.role = FactoryRole.getRoleParDefault();
    }

    @Test
    void userValide() throws UserException, RoleException {
        this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
        Assertions.assertTrue(this.testAdresse() && this.testMail() && this.testNom() && this.testPrenom() && this.testTel() && this.testMail() && this.testMotDePasse());
    }

    @ParameterizedTest
    @ValueSource(strings = { "stephan.parichon@epsifr", "stephan.parichonepsi.fr","","Stephan@g.c","stephan","stephan@gmail.c" })
    void userInvalideMail(final String param){
        boolean valide;
        try{
            this.userRegistrationDto.setMail(param);
            this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
            valide = true;
        }catch(final UserException | RoleException e){
            valide = !e.getMessage().equals(MessageException.MESSAGE_EXCEPTION_MAIL);
        }
        Assertions.assertFalse(valide);
    }

    @ParameterizedTest
    @ValueSource(strings = { "stephan.paricon@epsi.fr", "s@a.fr","Stephan@gmail.com","Stephan@g.com","Stephan@g.fr"})
    void userValideMail(final String param){
        boolean valide;
        try{
            this.userRegistrationDto.setMail(param);
            this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
            Assertions.assertTrue(true);
            valide = true;
        }catch(final UserException | RoleException e){
            valide = !e.getMessage().equals(MessageException.MESSAGE_EXCEPTION_MAIL);
        }
        Assertions.assertTrue(valide);
    }

    @ParameterizedTest
    @ValueSource(strings = { "064560643", "","06456064311","+33645606439" })
    void userInvalideTelephone(final String param){
        boolean valide;
        try{
            this.userRegistrationDto.setTelephone(param);
            this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
            valide = true;
        }catch(final UserException | RoleException e){
            valide = !e.getMessage().equals(MessageException.MESSAGE_EXCEPTION_TELEPHONE);
        }
        Assertions.assertFalse(valide);
    }

    @ParameterizedTest
    @ValueSource(strings = { "","  " })
    void userNomInvalide(final String param){
        boolean valide;
        try {
            this.userRegistrationDto.setNom(param);
            this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
            valide = true;
        } catch (final UserException | RoleException e) {
            valide = !e.getMessage().equals(MessageException.MESSAGE_EXCEPTION_NOM);
        }
        Assertions.assertFalse(valide);
    }

    @ParameterizedTest
    @ValueSource(strings = { "","  " })
    void userPrenomInvalide(final String param){
        boolean valide;
        try {
            this.userRegistrationDto.setPrenom(param);
            this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
            valide = true;
        } catch (final UserException  | RoleException e) {
            valide = !e.getMessage().equals(MessageException.MESSAGE_EXCEPTION_PRENOM);
        }
        Assertions.assertFalse(valide);
    }

    @ParameterizedTest
    @ValueSource(strings = { "","  " })
    void userAdresseInvalide(final String param){
        boolean valide;
        try {
            this.userRegistrationDto.setAdresse(param);
            this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
            valide = true;
        } catch (final UserException  | RoleException e) {
            valide = !e.getMessage().equals(MessageException.MESSAGE_EXCEPTION_ADRESSE);
        }
        Assertions.assertFalse(valide);
    }

    private boolean testMail(){
        return this.user.getMail().equals(VALIDE_MAIL);
    }
    private boolean testAdresse(){
        return this.user.getAdresse().equals(VALIDE_ADRESSE);
    }
    private boolean testNom(){
        return this.user.getNom().equals(VALIDE_NOM);
    }
    private boolean testPrenom(){
        return this.user.getPrenom().equals(VALIDE_PRENOM);
    }
    private boolean testTel(){
        return this.user.getTelephone().equals(VALIDE_TELEPHONE);
    }
    private boolean testMotDePasse(){
        return this.user.getPassword().equals(VALIDE_MOT_DE_PASSE);
    }
}
