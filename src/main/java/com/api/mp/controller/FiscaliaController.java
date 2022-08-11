package com.api.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.mp.dto.GenericResponse;
import com.api.mp.entity.Fiscalia;
import com.api.mp.services.FiscaliaService;
import com.api.mp.utils.GeneralException;
import java.util.List;
import static com.api.mp.utils.Constants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/fiscalia")
public class FiscaliaController {
	
	@Autowired
	FiscaliaService serviceFiscalia;
	
	@GetMapping
	public ResponseEntity<GenericResponse<List<Fiscalia>>> listFiscalias() throws GeneralException {
		GenericResponse<List<Fiscalia>> response = new GenericResponse<List<Fiscalia>>();
		HttpStatus status = HttpStatus.OK;
		try 
		{
			List<Fiscalia> listFiscalias = serviceFiscalia.listFiscalias();
			response.setData(listFiscalias);
			response.setMsg("Lista de fiscalias");
		} 
		catch (GeneralException e) 
		{
			response.setStatus(false);
			response.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		catch (Exception e) {
			response.setStatus(false);
			response.setMsg("Error de servidor al listar las fiscalias.");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<GenericResponse<List<Fiscalia>>>(response,status);
	}
	
	@PostMapping
	public ResponseEntity<GenericResponse<Fiscalia>> saveFiscalia(@RequestBody Fiscalia fiscalia) {
		GenericResponse<Fiscalia> response = new GenericResponse<Fiscalia>();
		HttpStatus status = HttpStatus.OK;
		try 
		{
			fiscalia = serviceFiscalia.saveFiscalia(fiscalia);
			response.setData(fiscalia);
			response.setMsg("Fiscalía almacenada con éxito");
		} 
		catch (GeneralException e) 
		{
			response.setStatus(false);
			response.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		catch (Exception e) {
			response.setStatus(false);
			response.setMsg("Error de servidor al agregar una fiscalía.");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<GenericResponse<Fiscalia>>(response, status);
	}
	
	@PutMapping
	public ResponseEntity<GenericResponse<Fiscalia>> updateFiscalia(@RequestBody Fiscalia fiscalia) throws GeneralException {
		GenericResponse<Fiscalia> response = new GenericResponse<Fiscalia>();
		HttpStatus status = HttpStatus.OK;
		try 
		{
			fiscalia = serviceFiscalia.updateFiscalia(fiscalia);
			response.setData(fiscalia);
			response.setMsg("Fiscalía actualizada con éxito");
		} 
		catch (GeneralException e) 
		{
			response.setStatus(false);
			response.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		catch (Exception e) {
			response.setStatus(false);
			response.setMsg("Error de servidor al editar una fiscalía.");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<GenericResponse<Fiscalia>>(response, status);
	}
	
	@DeleteMapping("/{idFiscalia}")
	public ResponseEntity<GenericResponse<String>> deleteFiscalia(
			@PathVariable(name = "idFiscalia", required = true) Long idFiscalia
			) {
		GenericResponse<String> response = new GenericResponse<String>();
		HttpStatus status = HttpStatus.OK;
		try 
		{
			serviceFiscalia.deleteFiscalia(idFiscalia);
			response.setMsg("Fiscalía eliminada con éxito.");
			response.setData("SUCCESS");
		}
		catch (GeneralException e) 
		{
			response.setStatus(false);
			response.setMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		catch (Exception e) {
			response.setStatus(false);
			response.setMsg("Error de servidor al eliminar una fiscalía.");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<GenericResponse<String>>(response,status);
	}
}
