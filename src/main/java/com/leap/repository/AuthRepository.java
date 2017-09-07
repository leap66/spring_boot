package com.leap.repository;

import com.leap.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public interface AuthRepository extends JpaRepository<Auth, Integer> {

  // 通过mobile查找
  public List<Auth> findByMobile(String mobile);

  // 通过id查找
  public List<Auth> findById(String id);
}
