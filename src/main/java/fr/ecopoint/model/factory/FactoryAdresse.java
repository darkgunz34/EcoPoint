package fr.ecopoint.model.factory;

import fr.ecopoint.model.entities.Adresse;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.web.dto.entities.UserModificationDto;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;

public final class FactoryAdresse {

    private FactoryAdresse(){
    }

    public static Adresse getAdresseFromCreation(final UserRegistrationDto userRegistrationDto){
        return new Adresse(userRegistrationDto.getRue(),userRegistrationDto.getCodePostal(),userRegistrationDto.getVille());
    }

    public static Adresse getAdresseFromUpdate(final User user , final UserModificationDto userModificationDto){
        Adresse adresse = user.getAdresse();
        adresse.setRue(userModificationDto.getRue());
        adresse.setVille(userModificationDto.getVille());
        adresse.setCodePostal(userModificationDto.getCodePostal());
        return adresse;
    }
}
