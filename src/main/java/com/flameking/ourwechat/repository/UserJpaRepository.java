package com.flameking.ourwechat.repository;

import com.flameking.ourwechat.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<JpaUser, Long> {
}
