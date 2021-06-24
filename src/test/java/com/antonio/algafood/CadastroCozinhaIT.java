package com.antonio.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.antonio.algafood.domain.model.Cozinha;
import com.antonio.algafood.domain.repository.CozinhaRepository;
import com.antonio.algafood.util.DatabaseCleaner;
import com.antonio.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //tomcat levanta a aplicação
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private static final int COZINHA_ID_INEXISTENTE = 100;

	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables(); //Limpando o banco de dados
		
		prepararDados(); //populando o banco
		
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given() //dado
			.accept(ContentType.JSON)
		.when() //quando
			.get()
		.then() //então
			.statusCode(HttpStatus.OK.value()); //status 200
	}
	
	@Test
	public void deveConter2Cozinhas_QuandoConsultarCozinhas() {
		given() //dado
			.accept(ContentType.JSON)
		.when() //quando
			.get()
		.then() //então
			.body("", hasSize(quantidadeCozinhasCadastradas));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body(jsonCorretoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	//GET /cozinhas/{cozinhaId}
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
	    given()
	        .pathParam("cozinhaId", cozinhaAmericana.getId())
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{cozinhaId}")
	    .then()
	        .statusCode(HttpStatus.OK.value())
	        .body("nome", equalTo(cozinhaAmericana.getNome()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		given() //dado
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when() //quando
			.get("/{cozinhaId}")
		.then() //então
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		Cozinha cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);
		
		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);
		
		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}
	
}
	