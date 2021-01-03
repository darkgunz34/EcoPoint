package fr.ecopoint.controleur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class seConnecterTest {

    private static final String URL_PAGE_CONNEXION = "/seconnecter";
    private static final String FORMULAIRE = "<form action=\"/seconnecter\" method=\"post\">";
    private static final String ATTRIBUTE_ERREUR_FORMULAIRE = "erreurMauvaisCompte";
    private static final String ADRESS_MAIL = "stephan.parichon1@gmail.com";
    private static final String MOT_DE_PASSE = "Azerty01";
    private static final String USER = "user";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_PAGE_CONNEXION))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("seconnecter"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testPostErrorParamEmpty() throws Exception {
        boolean valide;
        final MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(URL_PAGE_CONNEXION))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("seconnecter"))
                .andReturn();
        final String content = result.getResponse().getContentAsString();
        System.out.println(content);
        valide = content.contains(URL_PAGE_CONNEXION);
        if(!valide) valide = content.contains(FORMULAIRE);
        Assertions.assertTrue(valide);
    }

    @Test
    public void testPostErrorParamBad() throws Exception {
        boolean valide;
        final MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(URL_PAGE_CONNEXION).param("mail", ADRESS_MAIL).param("motDePasse",MOT_DE_PASSE.concat("error")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("seconnecter"))
                .andExpect(MockMvcResultMatchers.model().attribute(ATTRIBUTE_ERREUR_FORMULAIRE, "Votre identifiant et votre mot de passe ne sont valide. Merci de les ressaisir"))
                .andReturn();
        final String content = result.getResponse().getContentAsString();
        System.out.println(content);
        valide = content.contains(URL_PAGE_CONNEXION);
        if(!valide) valide = content.contains(FORMULAIRE);
        Assertions.assertTrue(valide);
    }

    @Test
    public void testPostSuccessParam() throws Exception {
        final MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(URL_PAGE_CONNEXION).param("mail", ADRESS_MAIL).param("motDePasse",MOT_DE_PASSE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists(USER))
                .andReturn();
        Assertions.assertTrue(true);
    }
}
