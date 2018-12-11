package com.demp.user.service;

import com.demp.user.domain.GRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GRole.
 */
public interface GRoleService {

    /**
     * Save a gRole.
     *
     * @param gRole the entity to save
     * @return the persisted entity
     */
    GRole save(GRole gRole);

    /**
     * Get all the gRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GRole> findAll(Pageable pageable);


    /**
     * Get the "id" gRole.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GRole> findOne(Long id);

    /**
     * Delete the "id" gRole.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
