package com.leap.controller;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.User;
import com.leap.model.convert.UserConvert;
import com.leap.model.out.Response;
import com.leap.service.connect.IUserServer;
import com.leap.util.IsEmpty;
import com.leap.util.ResultUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
   * @return List<User>
   */
  @GetMapping(value = "/query")
  public Response queryUser() throws BaseException {
    List<User> userList = service.query();
    if (IsEmpty.list(userList))
      userList = new ArrayList<>();
    return ResultUtil.success(userList, userList.size(), false);
  }

  /**
   * 查询用户
   *
   * @return User
   */
  @GetMapping(value = "/get")
  public Response get(@RequestParam("id") String id) throws BaseException {
    return ResultUtil.success(UserConvert.UserToA(service.get(id)));
  }

  /**
   * 更新用户
   *
   * @return User
   */
  @PostMapping(value = "/update")
  public Response update(@RequestBody @Valid User user, BindingResult result) throws BaseException {
    ValidUtil.valid(result);
    return ResultUtil.success(UserConvert.UserToA(service.update(user)));
  }
}
