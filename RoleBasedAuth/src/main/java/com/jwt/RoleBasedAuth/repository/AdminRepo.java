package com.jwt.RoleBasedAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.RoleBasedAuth.entity.UserEntity;

@Repository
public interface AdminRepo extends JpaRepository<UserEntity, Long> {

}
