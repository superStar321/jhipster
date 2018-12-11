package com.demp.user.repository;

import com.demp.user.domain.GuestRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GuestRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GuestRoleRepository extends JpaRepository<GuestRole, Long> {

}
