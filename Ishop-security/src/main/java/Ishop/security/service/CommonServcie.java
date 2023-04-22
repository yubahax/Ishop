package com.example.ishop.service;



import com.example.ishop.entity.User;

import java.util.List;
import java.util.concurrent.Future;


public interface CommonServcie {

    Future<List<User>> getAllAsync();

    List<User> getAll();


    String login(User user);
}
