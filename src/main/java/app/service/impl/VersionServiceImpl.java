package app.service.impl;

import app.service.VersionService;
import app.domain.Version;
import app.repository.VersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Version.
 */
@Service
@Transactional
public class VersionServiceImpl implements VersionService{

    private final Logger log = LoggerFactory.getLogger(VersionServiceImpl.class);
    
    @Inject
    private VersionRepository versionRepository;

    /**
     * Save a version.
     *
     * @param version the entity to save
     * @return the persisted entity
     */
    public Version save(Version version) {
        log.debug("Request to save Version : {}", version);
        Version result = versionRepository.save(version);
        return result;
    }

    /**
     *  Get all the versions.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Version> findAll(Pageable pageable) {
        log.debug("Request to get all Versions");
        Page<Version> result = versionRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one version by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Version findOne(Long id) {
        log.debug("Request to get Version : {}", id);
        Version version = versionRepository.findOne(id);
        return version;
    }

    /**
     *  Delete the  version by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Version : {}", id);
        versionRepository.delete(id);
    }

	@Override
	public List<Version> findAllVersion(Long id) {
		// TODO Auto-generated method stub
		return versionRepository.findByAplicacionId(id);
	}
}
