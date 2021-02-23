package fr.ecopoint.web.dto.entities.factory;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.web.dto.entities.UserAccueilDto;

/**
 * Ensemble des méthodes pour instancier un role
 */
public final class FactoryUserAccueilDto {
    /**
     * Constructeur privé.
     */
    private FactoryUserAccueilDto(){
    }

    /**
     * Méthode pour retourner le rôle avec les valeurs par défaut.
     * @param user Le user depuis la bdd à convertir.
     * @param nombrePointTotal le nombre de point total de l'utilisateur.
     * @return Le rôle par défaut.
     */
    public static UserAccueilDto getRoleParDefault(final User user,final int nombrePointTotal){
        final UserAccueilDto userAccueilDto = new UserAccueilDto();
        userAccueilDto.setNom(user.getNom());
        userAccueilDto.setPrenom(user.getPrenom());
        userAccueilDto.setNombreTotalPoint(nombrePointTotal);
        return userAccueilDto;
    }

}
