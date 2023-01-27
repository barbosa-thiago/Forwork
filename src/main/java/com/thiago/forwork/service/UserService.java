package com.thiago.forwork.service;

import com.thiago.forwork.dto.user.UserSaveDTO;
import com.thiago.forwork.dto.user.UserUpdateDTO;
import com.thiago.forwork.filter.UserFilter;
import com.thiago.forwork.mapper.UserMapper;
import com.thiago.forwork.model.User;
import com.thiago.forwork.repository.UserRepository;
import com.thiago.forwork.repository.specification.UserSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(UserSaveDTO body) {
        var user = UserMapper.INSTANCE.userSaveDTOToUser(body);
        return userRepository.save(user);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> getByNameAndPeriod(UserFilter filter) {
        var query = UserSpec.name(filter.getName())
            .and(UserSpec.period(filter.getStart(), filter.getEnd()));
        return userRepository.findAll(query, filter.pageRequest());
    }

    public User update(Long id, UserUpdateDTO body) {
        var user = getByID(id);

        UserMapper.INSTANCE.update(body, user);
        return userRepository.save(user);
    }

    public User getByID(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found"));
    }

    public void delete(Long id) {
        var user = getByID(id);
        userRepository.delete(user);
    }
}
