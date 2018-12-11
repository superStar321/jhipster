package com.demp.user.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demp.user.domain.GRole;
import com.demp.user.service.GRoleService;
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
 * REST controller for managing GRole.
 */
@RestController
@RequestMapping("/api")
public class GRoleResource {

    private final Logger log = LoggerFactory.getLogger(GRoleResource.class);

    private static final String ENTITY_NAME = "gRole";

    private final GRoleService gRoleService;

    public GRoleResource(GRoleService gRoleService) {
        this.gRoleService = gRoleService;
    }

    /**
     * POST  /g-roles : Create a new gRole.
     *
     * @param gRole the gRole to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gRole, or with status 400 (Bad Request) if the gRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/g-roles")
    @Timed
    public ResponseEntity<GRole> createGRole(@RequestBody GRole gRole) throws URISyntaxException {
        log.debug("REST request to save GRole : {}", gRole);
        if (gRole.getId() != null) {
            throw new BadRequestAlertException("A new gRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GRole result = gRoleService.save(gRole);
        return ResponseEntity.created(new URI("/api/g-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /g-roles : Updates an existing gRole.
     *
     * @param gRole the gRole to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gRole,
     * or with status 400 (Bad Request) if the gRole is not valid,
     * or with status 500 (Internal Server Error) if the gRole couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/g-roles")
    @Timed
    public ResponseEntity<GRole> updateGRole(@RequestBody GRole gRole) throws URISyntaxException {
        log.debug("REST request to update GRole : {}", gRole);
        if (gRole.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GRole result = gRoleService.save(gRole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gRole.getId().toString()))
            .body(result);
    }

    /**
     * GET  /g-roles : get all the gRoles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gRoles in body
     */
    @GetMapping("/g-roles")
    @Timed
    public ResponseEntity<List<GRole>> getAllGRoles(Pageable pageable) {
        log.debug("REST request to get a page of GRoles");
        Page<GRole> page = gRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/g-roles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /g-roles/:id : get the "id" gRole.
     *
     * @param id the id of the gRole to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gRole, or with status 404 (Not Found)
     */
    @GetMapping("/g-roles/{id}")
    @Timed
    public ResponseEntity<GRole> getGRole(@PathVariable Long id) {
        log.debug("REST request to get GRole : {}", id);
        Optional<GRole> gRole = gRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gRole);
    }

    /**
     * DELETE  /g-roles/:id : delete the "id" gRole.
     *
     * @param id the id of the gRole to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/g-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteGRole(@PathVariable Long id) {
        log.debug("REST request to delete GRole : {}", id);
        gRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
