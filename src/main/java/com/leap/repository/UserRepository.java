package com.leap.repository;

import com.leap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author  : ylwei
 * @time    : 2017/9/5
 * @description :
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  // 通过id查找
  public List<User> findById(String id);

  // 通过id查找
  public List<User> findByNormal(boolean normal);
}
