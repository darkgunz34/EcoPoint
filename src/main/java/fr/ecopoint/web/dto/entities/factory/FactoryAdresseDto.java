package fr.ecopoint.web.dto.entities.factory;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.web.dto.entities.AdresseRegistrationDto;
import fr.ecopoint.web.dto.entities.UserAccueilDto;


public final class FactoryAdresseDto {

    private FactoryAdresseDto(){
    }

    public static AdresseRegistrationDto getAdresseRegistrationDto(){
        return new AdresseRegistrationDto();
    }

}
