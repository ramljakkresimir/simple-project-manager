package com.spm.repositories;

import com.spm.models.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProject, Integer> {

}
