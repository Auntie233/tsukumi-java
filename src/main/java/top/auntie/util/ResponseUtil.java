package top.auntie.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import top.auntie.model.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResponseUtil {

    public static void responseWriter(HttpServletResponse response, String message, int httpStatus) throws IOException {
        response.setStatus(httpStatus);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (Writer writer = response.getWriter()) {
            writer.write(JSON.toJSONString(Result.failed(httpStatus, message)));
        }
    }

}
