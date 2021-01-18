package top.auntie.controller.grumble;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import top.auntie.model.common.Result;
import top.auntie.model.dto.GrumbleDto;
import top.auntie.model.dto.GrumbleSearchDto;
import top.auntie.model.vo.GrumbleVo;
import top.auntie.service.GrumbleService;

@RestController
public class GrumbleController {

    private final GrumbleService grumbleService;

    public GrumbleController(GrumbleService grumbleService) {
        this.grumbleService = grumbleService;
    }

    @PostMapping("/{type}/grumble")
    public Result postGrumble(@RequestBody GrumbleDto grumbleDto, @PathVariable("type") String type) {
        grumbleDto.setType(type);
        grumbleService.postGrumble(grumbleDto);
        return Result.success(grumbleDto);
    }

    @GetMapping("/{type}/grumbles")
    public Result<PageInfo<GrumbleVo>> getGrumbleList(GrumbleSearchDto dto, @PathVariable("type") String type) {
        return Result.success(grumbleService.getGrumbleList(dto, type));
    }

}
