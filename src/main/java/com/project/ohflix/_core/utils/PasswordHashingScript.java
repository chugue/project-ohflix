package com.project.ohflix._core.utils;


import com.project.ohflix.domain.user.User;
import com.project.ohflix.domain.user.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// 기존의 DB의 패스워드를 전부 해시화 업데이트
@Component
public class PasswordHashingScript implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.getPassword().startsWith("$2a$")) { // 비밀번호가 해시된 상태가 아닌 경우
                String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                user.setPassword(hashedPassword);
                userRepository.save(user);
            }
        }
    }
}
