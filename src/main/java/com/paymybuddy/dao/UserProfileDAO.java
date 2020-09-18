package com.paymybuddy.dao;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.entity.UserProfile;

public interface UserProfileDAO extends CrudRepository<UserProfile, Long>{

}
