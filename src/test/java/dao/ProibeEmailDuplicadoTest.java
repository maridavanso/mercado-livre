/*
 * package dao;
 * 
 * import java.util.Optional;
 * 
 * import org.junit.jupiter.api.Assertions; import
 * org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.params.ParameterizedTest; import
 * org.junit.jupiter.params.provider.MethodSource; import org.mockito.Mockito;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.validation.BeanPropertyBindingResult; import
 * org.springframework.validation.Errors;
 * 
 * import br.com.apizup.mercadolivre.cadastrousuario.NovoUsuarioRequest; import
 * br.com.apizup.mercadolivre.cadastrousuario.ProibeEmailDuplicado; import
 * br.com.apizup.mercadolivre.cadastrousuario.Usuario; import
 * br.com.apizup.mercadolivre.cadastrousuario.UsuarioRepository;
 * 
 * @SpringBootTest public class ProibeEmailDuplicadoTest {
 * 
 * @DisplayName("deveria lidar com email duplicado")
 * 
 * @ParameterizedTest
 * 
 * @MethodSource("geradorTest1") void test1(Optional<Usuario> possivelEmail,
 * boolean esperado) throws Exception{ UsuarioRepository usuarioRepository =
 * Mockito .mock(UsuarioRepository.class); ProibeEmailDuplicado validador = new
 * ProibeEmailDuplicado(); Object target = new
 * NovoUsuarioRequest("email@email.com", "senhaa"); Errors errors = new
 * BeanPropertyBindingResult(target, "teste");
 * Mockito.when(usuarioRepository.findByEmail("email@email.com"))
 * .thenReturn(possivelEmail);
 * 
 * validador.validate(target, errors);
 * 
 * Assertions.assertEquals(esperado, errors.hasFieldErrors("email")); }
 * 
 * }
 */