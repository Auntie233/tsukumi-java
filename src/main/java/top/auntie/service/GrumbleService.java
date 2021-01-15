package top.auntie.service;

import com.github.pagehelper.PageInfo;
import top.auntie.model.dto.GrumbleDto;
import top.auntie.model.dto.GrumbleSearchDto;
import top.auntie.model.vo.GrumbleVo;

public interface GrumbleService {
    void postGrumble(GrumbleDto grumbleDto);

    PageInfo<GrumbleVo> getGrumbleList(GrumbleSearchDto dto, String type);
}
