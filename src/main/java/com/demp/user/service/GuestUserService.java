package com.demp.user.service;

import com.demp.user.domain.GuestUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GuestUser.
 */
public interface GuestUserService {

    /**
     * Save a guestUser.
     *
     * @param guestUser the entity to save
     * @return the persisted entity
     */
    GuestUser save(GuestUser guestUser);

    /**
     * Get all the guestUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GuestUser> findAll(Pageable pageable);


    /**
     * Get the "id" guestUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GuestUser> findOne(Long id);

    /**
     * Delete the "id" guestUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
