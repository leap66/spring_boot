package com.leap.controller;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.User;
import com.leap.model.out.Response;
import com.leap.service.connect.IUserServer;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ylwei 2017/9/3
 */

@RestController
@RequestMapping(value = "/leap/app/user")
public class UserController {

  private final IUserServer service;

  @Autowired
  public UserController(IUserServer service) {
    this.service = service;
  }

  /**
   * 查询所有用户
   *
   * @return Response
   */
  @GetMapping(value = "/query")
  public Response queryUser() throws BaseException {
    return service.query();
  }

  /**
   * 查询用户
   *
   * @return Response
   */
  @GetMapping(value = "/get")
  public Response get(@RequestParam("id") String id) throws BaseException {
    return service.get(id);
  }

  /**
   * 更新用户
   *
   * @return Response
   */
  @PostMapping(value = "/update")
  public Response update(@RequestBody @Valid User user, BindingResult result) throws BaseException {
    ValidUtil.valid(result);
    return service.update(user);
  }
}
