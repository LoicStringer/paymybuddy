package com.paymybuddy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.entity.Account;
import com.paymybuddy.entity.Friendship;
import com.paymybuddy.entity.FriendshipPK;

public interface FriendshipDAO extends JpaRepository<Friendship, FriendshipPK>{

	List<Friendship> findByMyAccount(Account myAccount);
	
	
}
