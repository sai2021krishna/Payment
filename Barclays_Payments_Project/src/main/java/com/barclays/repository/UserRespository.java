package com.barclays.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.User;

public interface UserRespository extends JpaRepository<User, Integer> {

}
