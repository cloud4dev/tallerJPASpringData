package es.biblioteca.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.biblioteca.entity.Categoria;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
@DisplayName("JUnit Test unitario repositorio Categoria")
public class CategoriaRepositoryTest {

	@Autowired
	CategoriaRepository categoriaRepository;


	@Test
	@DisplayName("Test unitario buscar todos")
	public void testFindAll() {

		  List<Categoria> categoria = categoriaRepository.findAll();

		  assertEquals(categoria.size(), 11);
	}


	@Test
	@DisplayName("Test unitario count")
	public void testCount() {

		  Long categorias = categoriaRepository.count();

		  assertEquals(categorias, 11);
	}

	@Test
	@DisplayName("Test unitario buscar por id")
	public void testFindById() {

		  Optional<Categoria> categoria = categoriaRepository.findById(3);

		  assertEquals(categoria.isPresent(), true);
		  assertEquals(categoria.get().getNombre(),"Terror");
		  assertEquals(categoria.get().getLibros().size(), 3);
		  log.debug("Libros de Terror:" + categoria.get().getLibros().size());
		  categoria.get().getLibros().stream().forEach((libro)-> {
			  log.debug("Libro:" + libro.getTitulo());
			  });

		  Optional<Categoria> categoriaFail = categoriaRepository.findById(50);

		  assertEquals(categoriaFail.isPresent(), false);

	}



}
