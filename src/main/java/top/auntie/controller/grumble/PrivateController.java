package top.auntie.controller.grumble;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.auntie.model.common.Result;
import top.auntie.model.dto.GrumbleDto;
import top.auntie.service.GrumbleService;

@RestController("/private")
public class PrivateController {

    private final GrumbleService grumbleService;

    public PrivateController(GrumbleService grumbleService) {
        this.grumbleService = grumbleService;
    }

    @PostMapping("/grumble")
    public Result postGrumble(@RequestBody GrumbleDto grumbleDto) {
        return Result.success(grumbleDto);
    }



}
