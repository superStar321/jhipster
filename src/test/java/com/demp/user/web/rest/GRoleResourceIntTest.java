package com.demp.user.web.rest;

import com.demp.user.JhipsterApp;

import com.demp.user.domain.GRole;
import com.demp.user.repository.GRoleRepository;
import com.demp.user.service.GRoleService;
import com.demp.user.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.demp.user.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GRoleResource REST controller.
 *
 * @see GRoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class GRoleResourceIntTest {

    @Autowired
    private GRoleRepository gRoleRepository;

    @Autowired
    private GRoleService gRoleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGRoleMockMvc;

    private GRole gRole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GRoleResource gRoleResource = new GRoleResource(gRoleService);
        this.restGRoleMockMvc = MockMvcBuilders.standaloneSetup(gRoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GRole createEntity(EntityManager em) {
        GRole gRole = new GRole();
        return gRole;
    }

    @Before
    public void initTest() {
        gRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createGRole() throws Exception {
        int databaseSizeBeforeCreate = gRoleRepository.findAll().size();

        // Create the GRole
        restGRoleMockMvc.perform(post("/api/g-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gRole)))
            .andExpect(status().isCreated());

        // Validate the GRole in the database
        List<GRole> gRoleList = gRoleRepository.findAll();
        assertThat(gRoleList).hasSize(databaseSizeBeforeCreate + 1);
        GRole testGRole = gRoleList.get(gRoleList.size() - 1);
    }

    @Test
    @Transactional
    public void createGRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gRoleRepository.findAll().size();

        // Create the GRole with an existing ID
        gRole.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGRoleMockMvc.perform(post("/api/g-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gRole)))
            .andExpect(status().isBadRequest());

        // Validate the GRole in the database
        List<GRole> gRoleList = gRoleRepository.findAll();
        assertThat(gRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGRoles() throws Exception {
        // Initialize the database
        gRoleRepository.saveAndFlush(gRole);

        // Get all the gRoleList
        restGRoleMockMvc.perform(get("/api/g-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gRole.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getGRole() throws Exception {
        // Initialize the database
        gRoleRepository.saveAndFlush(gRole);

        // Get the gRole
        restGRoleMockMvc.perform(get("/api/g-roles/{id}", gRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gRole.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGRole() throws Exception {
        // Get the gRole
        restGRoleMockMvc.perform(get("/api/g-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGRole() throws Exception {
        // Initialize the database
        gRoleService.save(gRole);

        int databaseSizeBeforeUpdate = gRoleRepository.findAll().size();

        // Update the gRole
        GRole updatedGRole = gRoleRepository.findById(gRole.getId()).get();
        // Disconnect from session so that the updates on updatedGRole are not directly saved in db
        em.detach(updatedGRole);

        restGRoleMockMvc.perform(put("/api/g-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGRole)))
            .andExpect(status().isOk());

        // Validate the GRole in the database
        List<GRole> gRoleList = gRoleRepository.findAll();
        assertThat(gRoleList).hasSize(databaseSizeBeforeUpdate);
        GRole testGRole = gRoleList.get(gRoleList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingGRole() throws Exception {
        int databaseSizeBeforeUpdate = gRoleRepository.findAll().size();

        // Create the GRole

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGRoleMockMvc.perform(put("/api/g-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gRole)))
            .andExpect(status().isBadRequest());

        // Validate the GRole in the database
        List<GRole> gRoleList = gRoleRepository.findAll();
        assertThat(gRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGRole() throws Exception {
        // Initialize the database
        gRoleService.save(gRole);

        int databaseSizeBeforeDelete = gRoleRepository.findAll().size();

        // Get the gRole
        restGRoleMockMvc.perform(delete("/api/g-roles/{id}", gRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GRole> gRoleList = gRoleRepository.findAll();
        assertThat(gRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GRole.class);
        GRole gRole1 = new GRole();
        gRole1.setId(1L);
        GRole gRole2 = new GRole();
        gRole2.setId(gRole1.getId());
        assertThat(gRole1).isEqualTo(gRole2);
        gRole2.setId(2L);
        assertThat(gRole1).isNotEqualTo(gRole2);
        gRole1.setId(null);
        assertThat(gRole1).isNotEqualTo(gRole2);
    }
}
