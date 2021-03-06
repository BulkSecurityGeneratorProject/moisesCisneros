package app.service.impl;

import app.service.ArtefactoService;
import app.comprobar.comprobarBower;
import app.comprobar.comprobarGitHub;
import app.comprobar.comprobarMaven;
import app.comprobar.comprobarNpm;
import app.domain.Artefacto;
import app.repository.ArtefactoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Artefacto.
 */
@Service
@Transactional
public class ArtefactoServiceImpl implements ArtefactoService{

    private final Logger log = LoggerFactory.getLogger(ArtefactoServiceImpl.class);
    
    @Inject
    private ArtefactoRepository artefactoRepository;

    /**
     * Save a artefacto.
     *
     * @param artefacto the entity to save
     * @return the persisted entity
     */
    public Artefacto save(Artefacto artefacto) {
        log.debug("Request to save Artefacto : {}", artefacto);
        Artefacto result = artefactoRepository.save(artefacto);
        return result;
    }

    /**
     *  Get all the artefactos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Artefacto> findAll(Pageable pageable) {
        log.debug("Request to get all Artefactos");
        Page<Artefacto> result = artefactoRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one artefacto by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Artefacto findOne(Long id) {
        log.debug("Request to get Artefacto : {}", id);
        Artefacto artefacto = artefactoRepository.findOne(id);
        return artefacto;
    }

    /**
     *  Delete the  artefacto by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Artefacto : {}", id);
        artefactoRepository.delete(id);
    }

	@Override
	public List<Artefacto> findAllbyVersion(Long id) {
		// TODO Auto-generated method stub
		return artefactoRepository.findByVersionId(id);
	}
	@Override
	public List<Artefacto> comprobarAllbyVersion(Long id) {
		// TODO Auto-generated method stub
		List<Artefacto> artefactos = artefactoRepository.findByVersionId(id);
		for (Artefacto artefacto : artefactos) {
			this.comprobarUno(artefacto.getId());
		}
		return artefactoRepository.findByVersionId(id);
	}

	
	
	
	@Override
	public Artefacto comprobarUno(Long id){
		Artefacto aComprobar = artefactoRepository.findOne(id);
        switch (aComprobar.getRepositorio().toUpperCase()) {
        
        case "BOWER":
        	aComprobar =  new comprobarBower().comprobar(aComprobar);
        	break;
        case "NPM":
        	aComprobar =  new comprobarNpm().comprobar(aComprobar);
        	break;
        case "MAVEN":
        	aComprobar =  new comprobarMaven().comprobar(aComprobar);
        	break;
        case "GITHUB":
        	aComprobar =  new comprobarGitHub().comprobar(aComprobar);
        	break;
        }	
        aComprobar.setComprobado(true);
        artefactoRepository.save(aComprobar);
	return aComprobar;}
	}
	
	
	
	
