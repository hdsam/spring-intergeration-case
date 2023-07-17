/**
  * Copyright 2023 bejson.com 
  */
package com.hdsam.springclouddemo.feigndemo.pojo;

import lombok.Data;

/**
 * Auto-generated: 2023-06-25 13:25:42
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class User {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

}