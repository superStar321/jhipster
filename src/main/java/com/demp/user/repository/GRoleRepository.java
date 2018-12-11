package com.demp.user.repository;

import com.demp.user.domain.GRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GRoleRepository extends JpaRepository<GRole, Long> {

}
