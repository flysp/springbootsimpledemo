package com.jxedu.web;

import com.jxedu.entity.UserDemo;
import com.jxedu.service.JetCacheServiceDemo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * @author libin
 * @version 1.0
 * @description
 * @data 2019/12/16 10:11
 */
@RestController
@RequestMapping("/jetcache")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JetCacheController {

       private final JetCacheServiceDemo jetCacheServiceDemo;

       @GetMapping("/user/{id}")
       public ResponseEntity<UserDemo> findByUserId(@PathVariable(value = "id") Integer userId){
           UserDemo userdemo = jetCacheServiceDemo.getUserById(userId);
           return ResponseEntity.ok(userdemo);
       }

       @PutMapping("/user/{id}")
      public ResponseEntity<Void> updateUser(@PathVariable(value = "id") Integer id,
                                             @RequestParam(value = "name") String name){
           UserDemo userDemo = new UserDemo(id, name);
           jetCacheServiceDemo.updateUser(userDemo);
           return ResponseEntity.ok(null);
       }

       @DeleteMapping("/user/{id}")
       public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Integer id){
           jetCacheServiceDemo.deleteUser(id);
           return ResponseEntity.ok(null);
       }

       @PostMapping("/demo/{id}")
       public ResponseEntity createCacheDemo(@PathVariable(value = "id") Integer id){
           UserDemo cacheDemo = jetCacheServiceDemo.createCacheDemo(id);
           return ResponseEntity.ok(cacheDemo);
       }

       @GetMapping("/demo/{id}")
       public ResponseEntity getCacheDemo(@PathVariable(value = "id") Integer id){
           UserDemo cacheDemo = jetCacheServiceDemo.getCacheDemo(id);
           return ResponseEntity.ok(cacheDemo);
       }

       @PutMapping("/demo/{id}")
       public ResponseEntity updateCacheDemo(@PathVariable(value = "id") Integer id,String name){
           jetCacheServiceDemo.updateCacheDemo(id,name);
           return ResponseEntity.ok(null);
       }

       @DeleteMapping("/demo/{id}")
      public ResponseEntity deleteCacheDemo(@PathVariable(value = "id") Integer id){
           jetCacheServiceDemo.deleteCacheDemo(id);
           return ResponseEntity.ok(null);
       }
}
