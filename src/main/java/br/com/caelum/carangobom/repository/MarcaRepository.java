package br.com.caelum.carangobom.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.carangobom.domain.Marca;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MarcaRepository {
	
	

    private EntityManager em;

    @Autowired
    public MarcaRepository(EntityManager em) {
        this.em = em;
    }

    public void delete(Marca marca) {
        em.remove(marca);
    }

    public Marca save(Marca marca) {
        em.persist(marca);
        return marca;
    }

    public Optional<Marca> findById(Long id) {
    	return Optional.ofNullable(em.find(Marca.class, id));
    }

    public List<Marca> findAllByOrderByNome() {
        return em.createQuery("select m from Marca m order by m.nome", Marca.class)
                .getResultList();
    }

}
