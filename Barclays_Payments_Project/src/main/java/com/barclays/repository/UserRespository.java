package com.barclays.repository;


import org.springframework.data.repository.CrudRepository;

import com.barclays.entity.User;

public interface UserRespository extends CrudRepository<User, Integer> {

}
