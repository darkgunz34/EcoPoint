package fr.ecopoint.web.dto.entities.factory;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.web.dto.entities.UserAccueilDto;

public final class FactoryUserAccueilDto {

    private FactoryUserAccueilDto(){
    }

    public static UserAccueilDto getRoleParDefault(final User user,final int nombrePointTotal){
        final UserAccueilDto userAccueilDto = new UserAccueilDto();
        userAccueilDto.setNom(user.getNom());
        userAccueilDto.setPrenom(user.getPrenom());
        userAccueilDto.setNombreTotalPoint(nombrePointTotal);
        return userAccueilDto;
    }

}
