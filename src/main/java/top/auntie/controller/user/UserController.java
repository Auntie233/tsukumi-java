package top.auntie.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.auntie.controller.grumble.BaseController;
import top.auntie.exception.UserException;
import top.auntie.model.common.Result;
import top.auntie.model.dto.RegisterDto;
import top.auntie.service.TsukumiUserService;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final TsukumiUserService tsukumiUserService;

    public UserController(TsukumiUserService tsukumiUserService) {
        this.tsukumiUserService = tsukumiUserService;
    }

    @PostMapping("/register")
    public Result userRegister(@RequestBody RegisterDto registerDto) {
        try {
            tsukumiUserService.register(registerDto);
            return Result.success();
        } catch (UserException e) {
            log.error("用户注册失败： {}", e.getMessage());
            return Result.failed(e.getMessage());
        }
    }

}
