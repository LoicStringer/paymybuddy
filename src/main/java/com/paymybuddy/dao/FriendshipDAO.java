package com.paymybuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Friendship;

public interface FriendshipDAO extends JpaRepository<Friendship, Friendship.FriendshipPK>{

}
