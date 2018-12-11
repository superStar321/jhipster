package com.demp.user.web.rest;

import com.demp.user.JhipsterApp;

import com.demp.user.domain.GuestUser;
import com.demp.user.repository.GuestUserRepository;
import com.demp.user.service.GuestUserService;
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
 * Test class for the GuestUserResource REST controller.
 *
 * @see GuestUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class GuestUserResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final Integer DEFAULT_ISABLE = 1;
    private static final Integer UPDATED_ISABLE = 2;

    @Autowired
    private GuestUserRepository guestUserRepository;

    @Autowired
    private GuestUserService guestUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGuestUserMockMvc;

    private GuestUser guestUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GuestUserResource guestUserResource = new GuestUserResource(guestUserService);
        this.restGuestUserMockMvc = MockMvcBuilders.standaloneSetup(guestUserResource)
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
    public static GuestUser createEntity(EntityManager em) {
        GuestUser guestUser = new GuestUser()
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .age(DEFAULT_AGE)
            .sex(DEFAULT_SEX)
            .isable(DEFAULT_ISABLE);
        return guestUser;
    }

    @Before
    public void initTest() {
        guestUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createGuestUser() throws Exception {
        int databaseSizeBeforeCreate = guestUserRepository.findAll().size();

        // Create the GuestUser
        restGuestUserMockMvc.perform(post("/api/guest-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guestUser)))
            .andExpect(status().isCreated());

        // Validate the GuestUser in the database
        List<GuestUser> guestUserList = guestUserRepository.findAll();
        assertThat(guestUserList).hasSize(databaseSizeBeforeCreate + 1);
        GuestUser testGuestUser = guestUserList.get(guestUserList.size() - 1);
        assertThat(testGuestUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGuestUser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testGuestUser.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testGuestUser.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testGuestUser.getIsable()).isEqualTo(DEFAULT_ISABLE);
    }

    @Test
    @Transactional
    public void createGuestUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = guestUserRepository.findAll().size();

        // Create the GuestUser with an existing ID
        guestUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGuestUserMockMvc.perform(post("/api/guest-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guestUser)))
            .andExpect(status().isBadRequest());

        // Validate the GuestUser in the database
        List<GuestUser> guestUserList = guestUserRepository.findAll();
        assertThat(guestUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGuestUsers() throws Exception {
        // Initialize the database
        guestUserRepository.saveAndFlush(guestUser);

        // Get all the guestUserList
        restGuestUserMockMvc.perform(get("/api/guest-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(guestUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].isable").value(hasItem(DEFAULT_ISABLE)));
    }
    
    @Test
    @Transactional
    public void getGuestUser() throws Exception {
        // Initialize the database
        guestUserRepository.saveAndFlush(guestUser);

        // Get the guestUser
        restGuestUserMockMvc.perform(get("/api/guest-users/{id}", guestUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(guestUser.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.isable").value(DEFAULT_ISABLE));
    }

    @Test
    @Transactional
    public void getNonExistingGuestUser() throws Exception {
        // Get the guestUser
        restGuestUserMockMvc.perform(get("/api/guest-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGuestUser() throws Exception {
        // Initialize the database
        guestUserService.save(guestUser);

        int databaseSizeBeforeUpdate = guestUserRepository.findAll().size();

        // Update the guestUser
        GuestUser updatedGuestUser = guestUserRepository.findById(guestUser.getId()).get();
        // Disconnect from session so that the updates on updatedGuestUser are not directly saved in db
        em.detach(updatedGuestUser);
        updatedGuestUser
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .age(UPDATED_AGE)
            .sex(UPDATED_SEX)
            .isable(UPDATED_ISABLE);

        restGuestUserMockMvc.perform(put("/api/guest-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGuestUser)))
            .andExpect(status().isOk());

        // Validate the GuestUser in the database
        List<GuestUser> guestUserList = guestUserRepository.findAll();
        assertThat(guestUserList).hasSize(databaseSizeBeforeUpdate);
        GuestUser testGuestUser = guestUserList.get(guestUserList.size() - 1);
        assertThat(testGuestUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGuestUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testGuestUser.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testGuestUser.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testGuestUser.getIsable()).isEqualTo(UPDATED_ISABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGuestUser() throws Exception {
        int databaseSizeBeforeUpdate = guestUserRepository.findAll().size();

        // Create the GuestUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGuestUserMockMvc.perform(put("/api/guest-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guestUser)))
            .andExpect(status().isBadRequest());

        // Validate the GuestUser in the database
        List<GuestUser> guestUserList = guestUserRepository.findAll();
        assertThat(guestUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGuestUser() throws Exception {
        // Initialize the database
        guestUserService.save(guestUser);

        int databaseSizeBeforeDelete = guestUserRepository.findAll().size();

        // Get the guestUser
        restGuestUserMockMvc.perform(delete("/api/guest-users/{id}", guestUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GuestUser> guestUserList = guestUserRepository.findAll();
        assertThat(guestUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GuestUser.class);
        GuestUser guestUser1 = new GuestUser();
        guestUser1.setId(1L);
        GuestUser guestUser2 = new GuestUser();
        guestUser2.setId(guestUser1.getId());
        assertThat(guestUser1).isEqualTo(guestUser2);
        guestUser2.setId(2L);
        assertThat(guestUser1).isNotEqualTo(guestUser2);
        guestUser1.setId(null);
        assertThat(guestUser1).isNotEqualTo(guestUser2);
    }
}
