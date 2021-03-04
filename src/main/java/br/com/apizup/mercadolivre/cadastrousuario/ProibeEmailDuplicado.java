
package br.com.apizup.mercadolivre.cadastrousuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicado implements Validator {

	// 1
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		// 1
		return NovoUsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 2
		if (errors.hasErrors()) {
			return;
		}

		NovoUsuarioRequest request = (NovoUsuarioRequest) target;

		Optional<Usuario> possivelUsuario = usuarioRepository.findByEmail(request.getEmail());

		// 3
		if (possivelUsuario.isPresent()) {
			errors.rejectValue("email", null, "ja existe este email no sistema");
		}
	}

}
