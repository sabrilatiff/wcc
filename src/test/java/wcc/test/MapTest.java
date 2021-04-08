package wcc.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import wcc.WCCApplication;
import wcc.config.SecurityConfig;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = WCCApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"wcc.service"})
@ContextConfiguration(classes = {SecurityConfig.class})
@TestPropertySource("classpath:application.properties")
@EntityScan(basePackages={"wcc.domain"})
public class MapTest {

	@Autowired
	//@MockBean
	private MockMvc mockMvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
			
		this.mockMvc.perform(get("/map/distance/AB10 1XG/AB23 8ET").header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString("admin:123".getBytes())))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.distance", is(4.156560764416806)));
		
			
	}
}