package top.auntie.controller.grumble;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.auntie.exception.ParametersException;
import top.auntie.model.common.Result;

@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(ParametersException.class)
    public Result paramsExceptionHandler(ParametersException e) {
        log.error("参数异常: {}", e.getMessage());
        return Result.failed(e.getMessage());
    }

}
