package fr.ecopoint.model;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryRole;
import fr.ecopoint.model.factory.FactoryUser;
import fr.ecopoint.web.dto.entities.UserLoginDto;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

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
    private UserLoginDto userLoginDto;

    @BeforeEach
    void init(){
        this.userRegistrationDto = new UserRegistrationDto();
        this.userRegistrationDto.setMail(VALIDE_MAIL);
//        this.userRegistrationDto.setAdresse(true);
        this.userRegistrationDto.setMotDePasse(VALIDE_MOT_DE_PASSE);
        this.userRegistrationDto.setMotDePasse2(VALIDE_MOT_DE_PASSE_2);
        this.userRegistrationDto.setNom(VALIDE_NOM);
        this.userRegistrationDto.setPrenom(VALIDE_PRENOM);
        this.userRegistrationDto.setTelephone(VALIDE_TELEPHONE);
        this.role = FactoryRole.getRoleParDefault();

        this.userLoginDto = new UserLoginDto();
        this.userLoginDto.setMail(VALIDE_MAIL);
        this.userLoginDto.setMotDePasse(VALIDE_MOT_DE_PASSE);
    }

    @Test
    void userValide() throws UserException, RoleException {
        this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
        Assertions.assertTrue(this.testAdresse() && this.testMail() && this.testNom() && this.testPrenom() && this.testTel() && this.testMail() && this.testMotDePasse() && this.testRole());
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
            valide = !e.getMessage().equals(MessageEx.MESSAGE_EXCEPTION_MAIL);
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
            valide = !e.getMessage().equals(MessageEx.MESSAGE_EXCEPTION_MAIL);
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
            valide = !e.getMessage().equals(MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
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
            valide = !e.getMessage().equals(MessageEx.MESSAGE_EXCEPTION_NOM);
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
            valide = !e.getMessage().equals(MessageEx.MESSAGE_EXCEPTION_PRENOM);
        }
        Assertions.assertFalse(valide);
    }

    @ParameterizedTest
    @ValueSource(strings = { "","  " })
    void userAdresseInvalide(final String param){
        boolean valide;
        try {
//            this.userRegistrationDto.setAdresse(true);
            this.user = FactoryUser.getUserFromCreation(this.userRegistrationDto, this.role);
            valide = true;
        } catch (final UserException  | RoleException e) {
            valide = !e.getMessage().equals(MessageEx.MESSAGE_EXCEPTION_ADRESSE);
        }
        Assertions.assertFalse(valide);
    }

    @Test
    void userFromLoginValide() throws RoleException, UserException {
        this.user = FactoryUser.getUserFromLogin(this.userLoginDto,this.role);
        Assertions.assertTrue(this.testMail() && this.testMotDePasse() && this.testRole());
    }

    @Test
    void userFromLoginMotDePasseNullInvalide() throws RoleException{
        boolean valide;
        try{
            this.userLoginDto.setMotDePasse(null);
            this.user = FactoryUser.getUserFromLogin(this.userLoginDto,this.role);
            valide= true;
        }catch(final UserException userException){
            valide = false;
        }
        Assertions.assertFalse(valide);
    }

    @Test
    void userFromLoginMotDePasseValide() throws RoleException{
        boolean valide;
        try{
            this.userLoginDto.setMotDePasse(" ");
            this.user = FactoryUser.getUserFromLogin(this.userLoginDto,this.role);
            valide= true;
        }catch(final UserException userException){
            valide = false;
        }
        Assertions.assertFalse(valide);
    }

    @Test
    void userFromLoginMailInvalideNull() throws RoleException{
        boolean valide;
        try{
            this.userLoginDto.setMail(null);
            this.user = FactoryUser.getUserFromLogin(this.userLoginDto,this.role);
            valide= true;
        }catch(final UserException userException){
            valide = false;
        }
        Assertions.assertFalse(valide);
    }

    @Test
    void userFromLoginMailInvalideVide() throws RoleException{
        boolean valide;
        try{
            this.userLoginDto.setMail(" ");
            this.user = FactoryUser.getUserFromLogin(this.userLoginDto,this.role);
            valide= true;
        }catch(final UserException userException){
            valide = false;
        }
        Assertions.assertFalse(valide);
    }

    private boolean testMail(){
        return this.user.getMail().equals(VALIDE_MAIL);
    }
    private boolean testAdresse(){
        return this.user.getAdresse().toString().equals(VALIDE_ADRESSE);
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
    private boolean testRole(){
        return this.user.getRole().equals(this.role);
    }
}
