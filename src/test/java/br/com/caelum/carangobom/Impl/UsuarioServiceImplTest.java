package br.com.caelum.carangobom.Impl;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import br.com.caelum.carangobom.controller.MarcaController;
//import br.com.caelum.carangobom.controller.UsuarioController;
//import br.com.caelum.carangobom.domain.Marca;
//import br.com.caelum.carangobom.domain.Usuario;
//import br.com.caelum.carangobom.repository.MarcaRepository;
//import br.com.caelum.carangobom.repository.UsuarioRepository;
//import br.com.caelum.carangobom.service.MarcaService;
//import br.com.caelum.carangobom.service.UsuarioService;
//import lombok.var;

//class UsuarioServiceImplTest {

//	private UsuarioService usuarioService;
//    private UsuarioController usuarioController;
//
//    private UriComponentsBuilder uriBuilder;
//    
//	private final String nome = "Bruno";
//    private final String email = "brunoviniciustica@gmail.com";
//	private final String senha = "$2a$10$v4y6XDWJ.WQMRR.3SEpFteKf8b0n3qNj0JTDPpY09KFObG2ELUiuu";
	
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//    @BeforeEach
//    public void configuraMock() {
//        openMocks(this);
//        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
//    }
//	
//	
//	@Test
//	void ListUser() {
//		
//		List<Usuario> usuario = new ArrayList<Usuario>();
//		usuario.add(new Usuario(nome, email, senha));
//
//        when(usuarioRepository.findByIdOrderNome())
//            .thenReturn(usuario);
//        
//        List<Usuario> resultado = usuarioRepository.findByIdOrderNome();
//        assertEquals(usuario, resultado);
//	}
	

//	@Test
////	void createNewUser() {
////	    Usuario newUser = new Usuario(nome, email, senha);
////	
////	    when(usuarioRepository.save(newUser))
////	        .then(invocation -> {
////	            Usuario usuarioSalva = invocation.getArgument(0, Usuario.class);
////	            usuarioSalva.setId(1L);
////	
////	            return usuarioSalva;
////	        });
////	    
////	    ResponseEntity<Usuario> resposta = usuarioController.cadastraUsuario(newUser);
////	    assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
////	    assertEquals("http://localhost:8080/usuario/1", resposta.getHeaders().getLocation().toString());
////	  }

//}


