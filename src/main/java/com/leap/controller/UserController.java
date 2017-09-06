package com.leap.controller;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.User;
import com.leap.model.network.Response;
import com.leap.service.UserService;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ylwei 2017/9/3
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
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
   * 查询用户
   *
   * @return Response
   */
  @GetMapping(value = "/get/{tranId}")
  public Response get(@PathVariable("tranId") Integer tranId) throws BaseException {
    return service.get(tranId);
  }

  /**
   * 新增用户
   *
   * @return Response
   */
  @PostMapping(value = "/save")
  public Response save(@RequestBody @Valid User user, BindingResult result) throws BaseException {
    ValidUtil.valid(result);
    return service.save(user);
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

  /**
   * 删除用户
   *
   * @return Response
   */
  @GetMapping(value = "/delete/{id}")
  public Response delete(@PathVariable Integer id) throws BaseException {
    return service.delete(id);
  }

  /**
   * 删除用户
   *
   * @return Response
   */
  @GetMapping(value = "/delete")
  public Response delete(String id) throws BaseException {
    return service.delete(id);
  }
}
