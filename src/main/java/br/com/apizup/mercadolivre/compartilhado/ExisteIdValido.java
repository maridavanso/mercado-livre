package br.com.apizup.mercadolivre.compartilhado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExisteIdValido implements ConstraintValidator<ExisteId, Long>{

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExisteId params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value");
		query.setParameter("value", value);	
	
		
		List<?> list = query.getResultList();
		Assert.isTrue(list.size() <=1, "aconteceu algo inesperado e você tem mais de um "+klass+" com o atributo "+domainAttribute+" com o valor = "+value);
		
		return !list.isEmpty();
	}
}