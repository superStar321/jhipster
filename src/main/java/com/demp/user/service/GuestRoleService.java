package com.demp.user.service;

import com.demp.user.domain.GuestRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GuestRole.
 */
public interface GuestRoleService {

    /**
     * Save a guestRole.
     *
     * @param guestRole the entity to save
     * @return the persisted entity
     */
    GuestRole save(GuestRole guestRole);

    /**
     * Get all the guestRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GuestRole> findAll(Pageable pageable);


    /**
     * Get the "id" guestRole.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GuestRole> findOne(Long id);

    /**
     * Delete the "id" guestRole.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
