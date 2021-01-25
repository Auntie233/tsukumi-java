package top.auntie.controller.grumble;

import org.springframework.web.bind.annotation.*;
import top.auntie.model.common.Result;
import top.auntie.model.dto.GrumbleDto;
import top.auntie.model.vo.GrumbleVo;
import top.auntie.service.GrumbleService;

@RestController
@RequestMapping("/reply")
public class ReplyController extends BaseController {

    private final GrumbleService grumbleService;

    public ReplyController(GrumbleService grumbleService) {
        this.grumbleService = grumbleService;
    }

    @PostMapping("/grumble")
    public Result replyGrumble(@RequestBody GrumbleDto grumbleDto) {
        if (grumbleDto.getReplyPost()==null) {
            return Result.failed("被评论Id不能为空");
        }
        grumbleDto.setType("reply");
        grumbleService.postGrumble(grumbleDto);
        return Result.success(grumbleDto);
    }

}
