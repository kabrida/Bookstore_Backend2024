package syksy2024.bookstore;

//mm. mockMvc:n get- ja post-metodit
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class RestBookControllerTest {


    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach // JUnit5
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}


	@Test
	public void statusOk() throws Exception {
		mockMvc.perform(get("/api/books")).andExpect(status().isOk());
	}

	@Test
	public void responseTypeApplicationJson() throws Exception {
		mockMvc.perform(get("/api/books"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// .andExpect(content().contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
				.andExpect(status().isOk());
	}

    @Test
    public void getBookByIdTest() throws Exception {
        mockMvc.perform(get("/book/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void newBook() throws Exception {
        String newBookJson = "{\"title\": \"Test Book\", \"author\": \"Test Author\", \"publicationYear\": 2024, \"isbn\": \"1-234-567-8\", \"price\": 9.99}";

        mockMvc.perform(MockMvcRequestBuilders.post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBookJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void editBookTest() throws Exception {
        Long bookId = 1L;
        String editedBookJson = "{\"title\": \"Edited Book\", \"author\": \"Edited Author\", \"publicationYear\": 2024, \"isbn\": \"1-234-567-8\", \"price\": 9.99}";

        mockMvc.perform(MockMvcRequestBuilders.put("/book/{id}", bookId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(editedBookJson))
            .andExpect(status().isOk());
}


    @Test
    public void deleteBookTest() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/{id}", bookId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
}



}
