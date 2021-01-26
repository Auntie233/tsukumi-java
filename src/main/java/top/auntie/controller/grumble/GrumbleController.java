package top.auntie.controller.grumble;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import top.auntie.model.common.Result;
import top.auntie.model.dto.GrumbleDto;
import top.auntie.model.dto.GrumbleSearchDto;
import top.auntie.model.vo.GrumbleVo;
import top.auntie.service.GrumbleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestController
public class GrumbleController extends BaseController {

    private final TokenStore tokenStore;

    private final GrumbleService grumbleService;

    public GrumbleController(TokenStore tokenStore, GrumbleService grumbleService) {
        this.tokenStore = tokenStore;
        this.grumbleService = grumbleService;
    }

    @PostMapping("/{type}/grumble")
    public Result postGrumble(@RequestBody GrumbleDto grumbleDto, @PathVariable("type") String type) {
        grumbleDto.setType(type);
        grumbleDto.setCreateTime(new Date());
        grumbleService.postGrumble(grumbleDto);
        return Result.success(grumbleDto);
    }

    @GetMapping("/{type}/grumbles")
    public Result<PageInfo<GrumbleVo>> getGrumbleList(GrumbleSearchDto dto, @PathVariable("type") String type, HttpServletRequest request) {
        return Result.success(grumbleService.getGrumbleList(dto, type));
    }

}
