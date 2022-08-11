package com.api.mp.services;

import java.util.List;

import com.api.mp.entity.Fiscalia;
import com.api.mp.utils.GeneralException;

public interface FiscaliaService {
	
	List<Fiscalia> listFiscalias() throws GeneralException;
	
	Fiscalia saveFiscalia(Fiscalia fiscalia) throws GeneralException;
	
	Fiscalia updateFiscalia(Fiscalia fiscalia) throws GeneralException;
	
	void deleteFiscalia(Long idFiscalia) throws GeneralException;

}
