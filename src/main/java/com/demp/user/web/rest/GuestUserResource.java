package com.demp.user.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demp.user.domain.GuestUser;
import com.demp.user.service.GuestUserService;
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
 * REST controller for managing GuestUser.
 */
@RestController
@RequestMapping("/api")
public class GuestUserResource {

    private final Logger log = LoggerFactory.getLogger(GuestUserResource.class);

    private static final String ENTITY_NAME = "guestUser";

    private final GuestUserService guestUserService;

    public GuestUserResource(GuestUserService guestUserService) {
        this.guestUserService = guestUserService;
    }

    /**
     * POST  /guest-users : Create a new guestUser.
     *
     * @param guestUser the guestUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new guestUser, or with status 400 (Bad Request) if the guestUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/guest-users")
    @Timed
    public ResponseEntity<GuestUser> createGuestUser(@RequestBody GuestUser guestUser) throws URISyntaxException {
        log.debug("REST request to save GuestUser : {}", guestUser);
        if (guestUser.getId() != null) {
            throw new BadRequestAlertException("A new guestUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GuestUser result = guestUserService.save(guestUser);
        return ResponseEntity.created(new URI("/api/guest-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /guest-users : Updates an existing guestUser.
     *
     * @param guestUser the guestUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated guestUser,
     * or with status 400 (Bad Request) if the guestUser is not valid,
     * or with status 500 (Internal Server Error) if the guestUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/guest-users")
    @Timed
    public ResponseEntity<GuestUser> updateGuestUser(@RequestBody GuestUser guestUser) throws URISyntaxException {
        log.debug("REST request to update GuestUser : {}", guestUser);
        if (guestUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GuestUser result = guestUserService.save(guestUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, guestUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /guest-users : get all the guestUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of guestUsers in body
     */
    @GetMapping("/guest-users")
    @Timed
    public ResponseEntity<List<GuestUser>> getAllGuestUsers(Pageable pageable) {
        log.debug("REST request to get a page of GuestUsers");
        Page<GuestUser> page = guestUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/guest-users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /guest-users/:id : get the "id" guestUser.
     *
     * @param id the id of the guestUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the guestUser, or with status 404 (Not Found)
     */
    @GetMapping("/guest-users/{id}")
    @Timed
    public ResponseEntity<GuestUser> getGuestUser(@PathVariable Long id) {
        log.debug("REST request to get GuestUser : {}", id);
        Optional<GuestUser> guestUser = guestUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(guestUser);
    }

    /**
     * DELETE  /guest-users/:id : delete the "id" guestUser.
     *
     * @param id the id of the guestUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/guest-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteGuestUser(@PathVariable Long id) {
        log.debug("REST request to delete GuestUser : {}", id);
        guestUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
