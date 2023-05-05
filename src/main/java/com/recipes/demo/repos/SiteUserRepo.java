package com.recipes.demo.repos;

import com.recipes.demo.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepo extends JpaRepository<SiteUser, Long> {
    public SiteUser getSiteUserByUserName(String userName);
}
