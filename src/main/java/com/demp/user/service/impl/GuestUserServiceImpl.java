package com.demp.user.service.impl;

import com.demp.user.service.GuestUserService;
import com.demp.user.domain.GuestUser;
import com.demp.user.repository.GuestUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing GuestUser.
 */
@Service
@Transactional
public class GuestUserServiceImpl implements GuestUserService {

    private final Logger log = LoggerFactory.getLogger(GuestUserServiceImpl.class);

    private final GuestUserRepository guestUserRepository;

    public GuestUserServiceImpl(GuestUserRepository guestUserRepository) {
        this.guestUserRepository = guestUserRepository;
    }

    /**
     * Save a guestUser.
     *
     * @param guestUser the entity to save
     * @return the persisted entity
     */
    @Override
    public GuestUser save(GuestUser guestUser) {
        log.debug("Request to save GuestUser : {}", guestUser);
        return guestUserRepository.save(guestUser);
    }

    /**
     * Get all the guestUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GuestUser> findAll(Pageable pageable) {
        log.debug("Request to get all GuestUsers");
        return guestUserRepository.findAll(pageable);
    }


    /**
     * Get one guestUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GuestUser> findOne(Long id) {
        log.debug("Request to get GuestUser : {}", id);
        return guestUserRepository.findById(id);
    }

    /**
     * Delete the guestUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GuestUser : {}", id);
        guestUserRepository.deleteById(id);
    }
}
