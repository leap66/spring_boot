//package com.leap.repository;
//
//import com.leap.model.Token;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.annotation.Transient;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
//import java.util.List;
//
///**
// * @author : ylwei
// * @time : 2017/9/12
// * @description :
// */
//@CacheConfig(cacheNames = "token")
//public interface TokenRepository extends PagingAndSortingRepository<Token, Integer> {
//
//  @Cacheable(key = "#p0")
//  public List<Token> findByKey(String key);
//
//  @CachePut(key = "#p0.id")
//  @Override
//  Token save(Token token);
//
//  @Transient
//  @Override
//  void delete(Token token);
//}
