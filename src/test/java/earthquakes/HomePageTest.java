package earthquakes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import earthquakes.controllers.HomeController;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomePageTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthControllerAdvice aca;
   
    @MockBean
    private ClientRegistrationRepository crr;
   
    @Test
    public void getHomePage_ContentType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void getHomePage_BootstrapLoaded() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath(BootstrapLiterals.bootstrapCSSXpath).exists());
        for (String s: BootstrapLiterals.bootstrapJSurls) {
            String jsXPath = String.format("//script[@src='%s']",s);
            mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
              .andExpect(status().isOk())
              .andExpect(xpath(jsXPath).exists());
        }
    }

    @Test
    public void getHomePage_hasCorrectBrand() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div/nav/a").exists())
	        .andExpect(xpath("/html/body/div/nav/a").string("proj01"));
    }

    @Test
    public void getHomePage_hasCorrectTitle() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("//title").exists())
                .andExpect(xpath("//title").string("CS56 Spring Boot Practice App"));
    }

    @Test
    public void getHomePage_hasEarthquakesLink() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[1]/a").exists())
                .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[1]/a").string("Earthquake Search"));
    }

    @Test
    public void getHomePage_hasLocationsLink() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[2]/a").exists())
                .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[2]/a").string("Locations Search"));
    }

    @Test
    public void getHomePage_hasCorrectUsersLink() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[3]/a").exists())
        .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[3]/a").string("Users"));
    }
}
