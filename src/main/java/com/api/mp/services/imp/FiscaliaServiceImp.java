package com.api.mp.services.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.api.mp.entity.Fiscalia;
import com.api.mp.services.FiscaliaService;
import com.api.mp.utils.GeneralException;

@Repository
@Transactional
public class FiscaliaServiceImp implements FiscaliaService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Fiscalia> listFiscalias() throws GeneralException {
		List<Fiscalia> fiscalias = em.createQuery("Select f from Fiscalia f",Fiscalia.class).getResultList();
		return fiscalias;
	}

	@Override
	public Fiscalia saveFiscalia(Fiscalia fiscalia) throws GeneralException {
		if(ObjectUtils.isEmpty(fiscalia.getName())) throw new GeneralException("El nombre de la fiscalía es obligatorio");
		if(ObjectUtils.isEmpty(fiscalia.getAgency())) throw new GeneralException("La agencia de la fiscalía es obligatorio");
		if(ObjectUtils.isEmpty(fiscalia.getAddress())) throw new GeneralException("La dirección de la fiscalía es obligatorio");
		if(ObjectUtils.isEmpty(fiscalia.getPhone())) throw new GeneralException("El teléfono(s) de la fiscalía es obligatorio");
		if(ObjectUtils.isEmpty(fiscalia.getDepartment())) throw new GeneralException("El departamento de la fiscalía es obligatorio");
		if(ObjectUtils.isEmpty(fiscalia.getTown())) throw new GeneralException("El municipio de la fiscalía es obligatorio");
		
		em.persist(fiscalia);
		return fiscalia;
	}

	@Override
	public Fiscalia updateFiscalia(Fiscalia fiscalia) throws GeneralException {
		if(ObjectUtils.isEmpty(fiscalia.getId())) throw new GeneralException("El id de la fiscalía es obligatorio");
		
		Fiscalia fiscaliaUpdate = em.find(Fiscalia.class, fiscalia.getId());
		
		if(ObjectUtils.isEmpty(fiscaliaUpdate)) throw new GeneralException("La fiscalía no existe");
		fiscaliaUpdate.setName(fiscalia.getName());
		fiscaliaUpdate.setAgency(fiscalia.getAgency());
		fiscaliaUpdate.setAddress(fiscalia.getAddress());
		fiscaliaUpdate.setPhone(fiscalia.getPhone());
		fiscaliaUpdate.setDepartment(fiscalia.getDepartment());
		fiscaliaUpdate.setTown(fiscalia.getTown());
		
		em.persist(fiscaliaUpdate);
		return fiscalia;
	}

	@Override
	public void deleteFiscalia(Long idFiscalia) throws GeneralException {
		Fiscalia fiscalia = em.find(Fiscalia.class, idFiscalia);
		if(ObjectUtils.isEmpty(fiscalia)) throw new GeneralException("La fiscalía no existe");
		
		em.remove(fiscalia);
	}

}
