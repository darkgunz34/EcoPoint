package fr.ecopoint.model;

import fr.ecopoint.model.constante.RoleConstante;
import fr.ecopoint.model.factory.FactoryRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleTest {

    @Test
    void testFactoryRole() {
        Assertions.assertEquals(RoleConstante.USER_ROLE_PAR_DEFAULT, FactoryRole.getRoleParDefault().getName());
    }
}
