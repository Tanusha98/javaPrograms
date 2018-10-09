package com.stackroute.userservice.repository;

import com.stackroute.userservice.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
}
