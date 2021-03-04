package br.com.apizup.mercadolivre.compartilhado.seguranca;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

	UserDetails map(Object shouldBeASystemUser);
}