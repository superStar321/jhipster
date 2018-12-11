package com.demp.user.service.impl;

import com.demp.user.service.GRoleService;
import com.demp.user.domain.GRole;
import com.demp.user.repository.GRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing GRole.
 */
@Service
@Transactional
public class GRoleServiceImpl implements GRoleService {

    private final Logger log = LoggerFactory.getLogger(GRoleServiceImpl.class);

    private final GRoleRepository gRoleRepository;

    public GRoleServiceImpl(GRoleRepository gRoleRepository) {
        this.gRoleRepository = gRoleRepository;
    }

    /**
     * Save a gRole.
     *
     * @param gRole the entity to save
     * @return the persisted entity
     */
    @Override
    public GRole save(GRole gRole) {
        log.debug("Request to save GRole : {}", gRole);
        return gRoleRepository.save(gRole);
    }

    /**
     * Get all the gRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GRole> findAll(Pageable pageable) {
        log.debug("Request to get all GRoles");
        return gRoleRepository.findAll(pageable);
    }


    /**
     * Get one gRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GRole> findOne(Long id) {
        log.debug("Request to get GRole : {}", id);
        return gRoleRepository.findById(id);
    }

    /**
     * Delete the gRole by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GRole : {}", id);
        gRoleRepository.deleteById(id);
    }
}
