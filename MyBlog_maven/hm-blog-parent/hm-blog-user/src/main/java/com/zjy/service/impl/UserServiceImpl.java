package com.zjy.service.impl;

import com.zjy.entity.User;
import com.zjy.respository.UserRepository;
import com.zjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * 用户服务接口实现.
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public User saveOrUpateUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void removeUser(Long id) {
        userRepository.deleteById(id);
	}

	@Override
	public User getUserById(Long id) {
        Optional<User> optionalUser =userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return null;
        }else{
            return optionalUser.get();
        }
	}


	@Override
	public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        // 模糊查询
        name = "%" + name + "%";
        Page<User> users = userRepository.findByNameLike(name, pageable);
        return users;
	}


	@Override
	public List<User> listUsersByUsernames(Collection<String> usernames) {
		return userRepository.findByUsernameIn(usernames);
	}

}
