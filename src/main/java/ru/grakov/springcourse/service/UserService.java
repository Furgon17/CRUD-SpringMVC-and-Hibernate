package ru.grakov.springcourse.service;

import ru.grakov.springcourse.models.User;

import java.util.List;

public interface UserService {
    List<User> index();
    User show(Integer id);
    void save(User user);
    void update(Integer id, User updatedUser);
    void delete(Integer id);
}
