package com.demp.user.repository;

import com.demp.user.domain.GuestUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GuestUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GuestUserRepository extends JpaRepository<GuestUser, Long> {

}
