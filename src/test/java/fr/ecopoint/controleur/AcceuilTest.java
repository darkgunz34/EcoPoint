package fr.ecopoint.controleur;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AcceuilTest {

    private static final String USER = "stephan";
    public static final String PAGE1 = "/accueil";
    public static final String PAGE2 = "/";
    public static final String NOM_PAGE = "accueil";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetNonConnecter1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PAGE1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(NOM_PAGE))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetNonConnecter2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PAGE2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(NOM_PAGE))
                .andDo(MockMvcResultHandlers.print());
    }
}
