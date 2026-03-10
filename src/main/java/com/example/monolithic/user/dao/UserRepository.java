package com.example.monolithic.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.monolithic.user.domain.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{

}
