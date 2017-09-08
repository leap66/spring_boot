package com.leap.util;

import com.leap.model.User;
import com.leap.model.out.OutUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/8
 * @description :
 */
public class ConvertUtil {

  // User convert OutUser
  public static OutUser UserToA(User user) {
    if (IsEmpty.object(user))
      return null;
    OutUser outUser = new OutUser();
    outUser.setId(user.getId());
    outUser.setVersion(user.getVersion());
    outUser.setCreated(user.getCreated());
    outUser.setLastModified(user.getLastModified());
    outUser.setBirth(user.getBirth());
    outUser.setName(user.getName());
    outUser.setShortName(user.getShortName());
    outUser.setMobile(user.getMobile());
    outUser.setEmail(user.getEmail());
    outUser.setIdCard(user.getIdCard());
    outUser.setRemark(user.getRemark());
    outUser.setEducation(user.getEducation());
    outUser.setPhotoName(user.getPhotoName());
    outUser.setPhotoUrl(user.getPhotoUrl());
    return outUser;
  }

  // UserLst convert OutUserList
  public static List<OutUser> UserListToA(List<User> userList) {
    List<OutUser> outUserList = new ArrayList<>();
    if (IsEmpty.object(userList))
      return null;
    for (User user : userList) {
      outUserList.add(UserToA(user));
    }
    return outUserList;
  }
}
