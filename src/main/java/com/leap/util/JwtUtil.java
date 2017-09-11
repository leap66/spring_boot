package com.leap.util;

import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @author : ylwei
 * @time : 2017/9/11
 * @description :
 */
public class JwtUtil {

  /**
   * 创建JWT
   * 
   * @param subject
   *          签名信息
   * 
   * @return JWT
   * 
   */
  public static String created(String subject) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    SecretKey key = generalKey();
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    JwtBuilder builder = Jwts.builder().setId(MarsConfig.JWT_ID).setIssuedAt(now)
        .setSubject(subject).signWith(signatureAlgorithm, key);// 设置算法
    // 设置过期时间
    long expMillis = nowMillis + MarsConfig.JWT_ttlMillis;
    Date exp = new Date(expMillis);
    builder.setExpiration(exp);
    return builder.compact();
  }

  /**
   * 解析JWT
   * 
   * @param jwt
   *          token
   * @return 签名信息
   * @throws BaseException
   *           Token过期异常
   */
  public static String parse(String jwt) throws BaseException {
    if (IsEmpty.string(jwt))
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
    try {
      SecretKey key = generalKey();
      Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
      return claims.getSubject();
    } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
      // jwt 已经过期，在设置jwt的时候如果设置了过期时间，这里会自动判断jwt是否已经过期，
      // 如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
      // jwt 解析错误
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
    }
  }

  /**
   * 由字符串生成加密key
   *
   * @return 加密的key
   */
  private static SecretKey generalKey() {
    String stringKey = MarsConfig.JWT_SECRET;
    byte[] encodedKey = Base64.decodeBase64(stringKey);
    return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
  }
}
