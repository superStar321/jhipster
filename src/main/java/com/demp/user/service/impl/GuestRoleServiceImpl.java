package com.demp.user.service.impl;

import com.demp.user.service.GuestRoleService;
import com.demp.user.domain.GuestRole;
import com.demp.user.repository.GuestRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing GuestRole.
 */
@Service
@Transactional
public class GuestRoleServiceImpl implements GuestRoleService {

    private final Logger log = LoggerFactory.getLogger(GuestRoleServiceImpl.class);

    private final GuestRoleRepository guestRoleRepository;

    public GuestRoleServiceImpl(GuestRoleRepository guestRoleRepository) {
        this.guestRoleRepository = guestRoleRepository;
    }

    /**
     * Save a guestRole.
     *
     * @param guestRole the entity to save
     * @return the persisted entity
     */
    @Override
    public GuestRole save(GuestRole guestRole) {
        log.debug("Request to save GuestRole : {}", guestRole);
        return guestRoleRepository.save(guestRole);
    }

    /**
     * Get all the guestRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GuestRole> findAll(Pageable pageable) {
        log.debug("Request to get all GuestRoles");
        return guestRoleRepository.findAll(pageable);
    }


    /**
     * Get one guestRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GuestRole> findOne(Long id) {
        log.debug("Request to get GuestRole : {}", id);
        return guestRoleRepository.findById(id);
    }

    /**
     * Delete the guestRole by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GuestRole : {}", id);
        guestRoleRepository.deleteById(id);
    }
}
