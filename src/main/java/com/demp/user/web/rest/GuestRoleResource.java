package com.demp.user.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demp.user.domain.GuestRole;
import com.demp.user.service.GuestRoleService;
import com.demp.user.web.rest.errors.BadRequestAlertException;
import com.demp.user.web.rest.util.HeaderUtil;
import com.demp.user.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing GuestRole.
 */
@RestController
@RequestMapping("/api")
public class GuestRoleResource {

    private final Logger log = LoggerFactory.getLogger(GuestRoleResource.class);

    private static final String ENTITY_NAME = "guestRole";

    private final GuestRoleService guestRoleService;

    public GuestRoleResource(GuestRoleService guestRoleService) {
        this.guestRoleService = guestRoleService;
    }

    /**
     * POST  /guest-roles : Create a new guestRole.
     *
     * @param guestRole the guestRole to create
     * @return the ResponseEntity with status 201 (Created) and with body the new guestRole, or with status 400 (Bad Request) if the guestRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/guest-roles")
    @Timed
    public ResponseEntity<GuestRole> createGuestRole(@RequestBody GuestRole guestRole) throws URISyntaxException {
        log.debug("REST request to save GuestRole : {}", guestRole);
        if (guestRole.getId() != null) {
            throw new BadRequestAlertException("A new guestRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GuestRole result = guestRoleService.save(guestRole);
        return ResponseEntity.created(new URI("/api/guest-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /guest-roles : Updates an existing guestRole.
     *
     * @param guestRole the guestRole to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated guestRole,
     * or with status 400 (Bad Request) if the guestRole is not valid,
     * or with status 500 (Internal Server Error) if the guestRole couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/guest-roles")
    @Timed
    public ResponseEntity<GuestRole> updateGuestRole(@RequestBody GuestRole guestRole) throws URISyntaxException {
        log.debug("REST request to update GuestRole : {}", guestRole);
        if (guestRole.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GuestRole result = guestRoleService.save(guestRole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, guestRole.getId().toString()))
            .body(result);
    }

    /**
     * GET  /guest-roles : get all the guestRoles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of guestRoles in body
     */
    @GetMapping("/guest-roles")
    @Timed
    public ResponseEntity<List<GuestRole>> getAllGuestRoles(Pageable pageable) {
        log.debug("REST request to get a page of GuestRoles");
        Page<GuestRole> page = guestRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/guest-roles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /guest-roles/:id : get the "id" guestRole.
     *
     * @param id the id of the guestRole to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the guestRole, or with status 404 (Not Found)
     */
    @GetMapping("/guest-roles/{id}")
    @Timed
    public ResponseEntity<GuestRole> getGuestRole(@PathVariable Long id) {
        log.debug("REST request to get GuestRole : {}", id);
        Optional<GuestRole> guestRole = guestRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(guestRole);
    }

    /**
     * DELETE  /guest-roles/:id : delete the "id" guestRole.
     *
     * @param id the id of the guestRole to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/guest-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteGuestRole(@PathVariable Long id) {
        log.debug("REST request to delete GuestRole : {}", id);
        guestRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
