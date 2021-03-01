/*
 * package br.com.apizup.mercadolivre.cadastrousuario;
 * 
 * import java.util.Optional; import java.util.Set;
 * 
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Component; import
 * org.springframework.validation.Errors; import
 * org.springframework.validation.Validator;
 * 
 * @Component public class ProibeEmailDuplicado implements Validator{
 * 
 * @Autowired private UsuarioRepository usuarioRepository;
 * 
 * @Override public boolean supports (Class<?> clazz) { return
 * NovoUsuarioRequest.class.isAssignableFrom(clazz); }
 * 
 * @Override public void validate (Object target, Errors errors) {
 * 
 * if (errors.hasErrors()) { return; }
 * 
 * NovoUsuarioRequest request = (NovoUsuarioRequest) target;
 * 
 * Optional<Usuario> possivelEmail =
 * usuarioRepository.findByEmail(request.getEmail());
 * 
 * if(possivelEmail.isPresent()) { errors.reject("email", null,
 * "esse email já existe no sistema"); }
 * 
 * }
 * 
 * 
 * }
 * 
 * 
 */