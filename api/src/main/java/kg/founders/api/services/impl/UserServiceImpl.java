package kg.founders.api.services.impl;

import kg.founders.api.converters.UserConverter;
import kg.founders.api.repositories.UserRepository;
import kg.founders.api.services.UserService;
import kg.founders.common.entities.user.User;
import kg.founders.common.models.user.UserModel;
import kg.founders.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder encoder;

    @Override
    public Page<UserModel> get(String search, Pageable pageable) {
        return userRepository.findAll(search, pageable).map(userConverter::convertFromEntity);
    }

    @Override
    public UserModel create(UserModel userModel) throws Exception {
        if (userRepository.existsByUsername(userModel.getUsername())) {
            throw new Exception("Ошибка: Такой логин уже используется!"); //todo message lang
        }

        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new Exception("Ошибка: Такой email уже используется!"); //todo message lang
        }
        User user = userConverter.convertFromModel(userModel);
        user.setPassword(encoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return userConverter.convertFromEntity(user);
    }

    @Override
    public UserModel update(UserModel userModel) {
        User user = userConverter.convertFromModel(userModel);
        user.setPassword(encoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return userConverter.convertFromEntity(user);
    }

    @Override
    public UserModel getCurrentUser() {
        return userRepository.findByUsername(getCurrentUsername()).map(userConverter::convertFromEntity).orElseThrow(NotFoundException::new);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // Если пользователь аутентифицирован с помощью UserDetails
            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
                System.out.println("Логин пользователя: " + username); //todo message lang

                // Выводим дополнительные данные о пользователе
                userDetails.getAuthorities().forEach(authority -> {
                    System.out.println("Роль пользователя: " + authority.getAuthority()); //todo message lang
                });
            } else {
                // Если аутентификация производится другим способом
                username = principal.toString();
                System.out.println("Логин пользователя: " + username); //todo message lang
            }
        } else {
            System.out.println("Пользователь не аутентифицирован."); //todo message lang
        }
        return username;
    }
}
