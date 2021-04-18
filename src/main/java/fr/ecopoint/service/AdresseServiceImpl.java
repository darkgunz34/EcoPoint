package fr.ecopoint.service;

import fr.ecopoint.model.entities.Adresse;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.repository.AdresseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AdresseServiceImpl implements AdresseService {

    private static final Logger logger = LogManager.getLogger(AdresseServiceImpl.class);
    private final AdresseRepository adresseRepository;

    public AdresseServiceImpl(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    @Override
    public boolean save(Adresse adresse) {
        logger.debug("save() : " + adresse.toString());
        Adresse adresse1 = this.adresseRepository.save(adresse);
        return exit(adresse1);
    }

    @Override
    public boolean exit(final Adresse adresse){
        return this.adresseRepository.findById(adresse.getId()).isPresent();
    }
}