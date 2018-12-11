package com.demp.user.web.rest;

import com.demp.user.JhipsterApp;

import com.demp.user.domain.GuestRole;
import com.demp.user.repository.GuestRoleRepository;
import com.demp.user.service.GuestRoleService;
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
 * Test class for the GuestRoleResource REST controller.
 *
 * @see GuestRoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class GuestRoleResourceIntTest {

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    @Autowired
    private GuestRoleRepository guestRoleRepository;

    @Autowired
    private GuestRoleService guestRoleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGuestRoleMockMvc;

    private GuestRole guestRole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GuestRoleResource guestRoleResource = new GuestRoleResource(guestRoleService);
        this.restGuestRoleMockMvc = MockMvcBuilders.standaloneSetup(guestRoleResource)
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
    public static GuestRole createEntity(EntityManager em) {
        GuestRole guestRole = new GuestRole()
            .roleName(DEFAULT_ROLE_NAME);
        return guestRole;
    }

    @Before
    public void initTest() {
        guestRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createGuestRole() throws Exception {
        int databaseSizeBeforeCreate = guestRoleRepository.findAll().size();

        // Create the GuestRole
        restGuestRoleMockMvc.perform(post("/api/guest-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guestRole)))
            .andExpect(status().isCreated());

        // Validate the GuestRole in the database
        List<GuestRole> guestRoleList = guestRoleRepository.findAll();
        assertThat(guestRoleList).hasSize(databaseSizeBeforeCreate + 1);
        GuestRole testGuestRole = guestRoleList.get(guestRoleList.size() - 1);
        assertThat(testGuestRole.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
    }

    @Test
    @Transactional
    public void createGuestRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = guestRoleRepository.findAll().size();

        // Create the GuestRole with an existing ID
        guestRole.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGuestRoleMockMvc.perform(post("/api/guest-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guestRole)))
            .andExpect(status().isBadRequest());

        // Validate the GuestRole in the database
        List<GuestRole> guestRoleList = guestRoleRepository.findAll();
        assertThat(guestRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGuestRoles() throws Exception {
        // Initialize the database
        guestRoleRepository.saveAndFlush(guestRole);

        // Get all the guestRoleList
        restGuestRoleMockMvc.perform(get("/api/guest-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(guestRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getGuestRole() throws Exception {
        // Initialize the database
        guestRoleRepository.saveAndFlush(guestRole);

        // Get the guestRole
        restGuestRoleMockMvc.perform(get("/api/guest-roles/{id}", guestRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(guestRole.getId().intValue()))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGuestRole() throws Exception {
        // Get the guestRole
        restGuestRoleMockMvc.perform(get("/api/guest-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGuestRole() throws Exception {
        // Initialize the database
        guestRoleService.save(guestRole);

        int databaseSizeBeforeUpdate = guestRoleRepository.findAll().size();

        // Update the guestRole
        GuestRole updatedGuestRole = guestRoleRepository.findById(guestRole.getId()).get();
        // Disconnect from session so that the updates on updatedGuestRole are not directly saved in db
        em.detach(updatedGuestRole);
        updatedGuestRole
            .roleName(UPDATED_ROLE_NAME);

        restGuestRoleMockMvc.perform(put("/api/guest-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGuestRole)))
            .andExpect(status().isOk());

        // Validate the GuestRole in the database
        List<GuestRole> guestRoleList = guestRoleRepository.findAll();
        assertThat(guestRoleList).hasSize(databaseSizeBeforeUpdate);
        GuestRole testGuestRole = guestRoleList.get(guestRoleList.size() - 1);
        assertThat(testGuestRole.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingGuestRole() throws Exception {
        int databaseSizeBeforeUpdate = guestRoleRepository.findAll().size();

        // Create the GuestRole

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGuestRoleMockMvc.perform(put("/api/guest-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guestRole)))
            .andExpect(status().isBadRequest());

        // Validate the GuestRole in the database
        List<GuestRole> guestRoleList = guestRoleRepository.findAll();
        assertThat(guestRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGuestRole() throws Exception {
        // Initialize the database
        guestRoleService.save(guestRole);

        int databaseSizeBeforeDelete = guestRoleRepository.findAll().size();

        // Get the guestRole
        restGuestRoleMockMvc.perform(delete("/api/guest-roles/{id}", guestRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GuestRole> guestRoleList = guestRoleRepository.findAll();
        assertThat(guestRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GuestRole.class);
        GuestRole guestRole1 = new GuestRole();
        guestRole1.setId(1L);
        GuestRole guestRole2 = new GuestRole();
        guestRole2.setId(guestRole1.getId());
        assertThat(guestRole1).isEqualTo(guestRole2);
        guestRole2.setId(2L);
        assertThat(guestRole1).isNotEqualTo(guestRole2);
        guestRole1.setId(null);
        assertThat(guestRole1).isNotEqualTo(guestRole2);
    }
}
