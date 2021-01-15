package top.auntie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.auntie.mapper.GrumbleInfoMapper;
import top.auntie.model.dto.GrumbleDto;
import top.auntie.model.dto.GrumbleSearchDto;
import top.auntie.model.vo.GrumbleVo;
import top.auntie.pojo.GrumbleInfo;
import top.auntie.service.GrumbleService;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Service
public class GrumbleServiceImpl implements GrumbleService {

    @Resource
    private GrumbleInfoMapper grumbleInfoMapper;

    @Override
    @Transactional
    public void postGrumble(GrumbleDto grumbleDto) {
        if (grumbleDto.getId() != null) {
            grumbleInfoMapper.updateByPrimaryKeySelective(grumbleDto);
        } else {
            grumbleInfoMapper.insertSelective(grumbleDto);
        }
    }

    @Override
    public PageInfo<GrumbleVo> getGrumbleList(GrumbleSearchDto dto, String type) {
        GrumbleInfo record = new GrumbleInfo();
        record.setType(type);
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        return new PageInfo<>(grumbleInfoMapper.select(record).stream().map(item -> {
            GrumbleVo grumbleVo = new GrumbleVo();
            BeanUtils.copyProperties(item, grumbleVo);
            return grumbleVo;
        }).collect(Collectors.toList()));
    }
}
