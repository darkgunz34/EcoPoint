package fr.ecopoint.model.utils;

import fr.ecopoint.model.entities.User;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component("javaMailSender")
public final class MailUtile {

    private final JavaMailSender javaMailSender;

    public MailUtile(final JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public boolean envoyerMailInscription(final User user) throws fr.ecopoint.model.exception.MailException {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(user.getMail());
        msg.setSubject("Inscription EcoPoint");

        String corp = "Bienvenue dans l’aventure Éco Point " + user.getPrenom() + " " + user.getNom() +" tu peux dès à présent utiliser nos bornes et commencer à trier tes déchets !";
        msg.setText(corp);

        try {
            javaMailSender.send(msg);
        }catch (MailException e){
            throw new fr.ecopoint.model.exception.MailException(String.format("Mail invalide : %s",user.getMail()));
        }
        return true;
    }
}
