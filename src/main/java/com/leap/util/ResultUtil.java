package com.leap.util;

import com.leap.model.out.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ylwei 2017/9/4
 */
public class ResultUtil {

  public static Response success(Object object, Integer total, Boolean more) {
    Response<Object> response = new Response<>();
    response.setCode(200);
    response.setData(object);
    response.setMore(more);
    response.setTotal(total);
    response.setSuccess(true);
    return response;
  }

  public static Response success(Object object) {
    return success(object, 0, false);
  }

  public static Response error(Integer code, String... errorMsg) {
    List<String> error = new ArrayList<>();
    error.addAll(Arrays.asList(errorMsg));
    Response<Object> response = new Response<>();
    response.setCode(code);
    response.setMessage(error);
    return response;
  }
}
