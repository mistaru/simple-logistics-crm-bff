package kg.founders.api.controllers;

import kg.founders.api.services.UserService;
import kg.founders.common.annotations.ResponseMessageController;
import kg.founders.common.models.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@ResponseMessageController("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<UserModel> get(@RequestParam(required = false) String search,
                               Pageable pageable) {
        return userService.get(search, pageable);
    }

    @PostMapping
    public UserModel create(@RequestBody UserModel userModel) throws Exception {
        return userService.create(userModel);
    }

    @PutMapping
    public UserModel update(@RequestBody UserModel userModel) {
        return userService.update(userModel);
    }
}
