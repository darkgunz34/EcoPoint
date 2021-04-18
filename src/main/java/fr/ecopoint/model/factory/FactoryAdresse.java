package fr.ecopoint.model.factory;

import fr.ecopoint.model.entities.Adresse;
import fr.ecopoint.model.exception.AdresseException;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.web.dto.entities.AdresseRegistrationDto;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FactoryAdresse {

    private static final Logger logger = LogManager.getLogger(FactoryAdresse.class);

    private FactoryAdresse(){
    }

    public static Adresse getAdresseFromCreation(final UserRegistrationDto userRegistrationDto){
        return new Adresse(userRegistrationDto.getRue(),userRegistrationDto.getCodePostal(),userRegistrationDto.getVille());
    }

    private static boolean champNonVide(final String chaine){
        return chaine != null && !chaine.trim().isEmpty();
    }
}
