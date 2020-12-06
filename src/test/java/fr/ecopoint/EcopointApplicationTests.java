package fr.ecopoint;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EcopointApplicationTests {

	@LocalServerPort
	private int port;

	@Test
	void contextLoads(){
		assertEquals(9090,port);
	}

}
