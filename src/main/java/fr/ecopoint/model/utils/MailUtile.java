package fr.ecopoint.model.utils;

import fr.ecopoint.model.constante.MailConstante;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component("javaMailSender")
public final class MailUtile {

    private final JavaMailSender javaMailSender;

    public MailUtile(final JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public boolean envoyerMailInscription(final User user) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom(MailConstante.MAIL_ECOPOINT);
        msg.setTo(user.getMail());
        msg.setSubject("Inscription EcoPoint");

        String corp = "Bienvenue dans l’aventure Éco Point " + user.getPrenom() + " " + user.getNom() +" tu peux dès à présent utiliser nos bornes et commencer à trier tes déchets !";
        msg.setText(corp);

        try {
            //TODO: Implémenter pour la prod. Attention à bien vérifier cette adresse (gmail) permettant d'authoriser les applications moins sécure https://myaccount.google.com/u/1/lesssecureapps?pli=1&rapt=AEjHL4MEnxqV3QQl2xeHdWVHTOmRyEY-QySQ7CnvT75UhheZfm0-8sn1b0uKJfL22eDjnSwyvZtwnaUfVxFsvRt7nT0Cd8tsng
            //javaMailSender.send(msg);
        }catch (org.springframework.mail.MailException e){
            throw new MailException(String.format("Mail invalide : %s",user.getMail()));
        }
        return true;
    }
}
