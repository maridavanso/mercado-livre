package br.com.apizup.mercadolivre.compartilhado;

import org.springframework.context.annotation.Configuration;

import br.com.apizup.mercadolivre.cadastrousuario.Usuario;
import br.com.apizup.mercadolivre.compartilhado.seguranca.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object shouldBeASystemUser) {						
		return new UsuarioLogado((Usuario)shouldBeASystemUser);
	}

}