package top.auntie.annotation;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


/**
 * 切面日志打印方法
 * @author tay
 */
@Component
@Aspect
public class ServiceLogAspect {

    protected final Logger logger = LogManager.getLogger(getClass());

    @Before(value = "@annotation(top.auntie.annotation.ServiceLog) && @annotation(serviceLog)")
    public void before(JoinPoint joinPoint , ServiceLog serviceLog) {
        if (serviceLog.isOutter()){
            logger.info("===============================");
        }
        logger.info("调用 " + serviceLog.value() + "开始 " + joinPoint.getSignature().getName() + " start");
        Signature sig = joinPoint.getSignature();
        if (sig instanceof MethodSignature) {
            logger.info("参数列表----|");
            MethodSignature msig = (MethodSignature) sig;
            String[] paramNames = msig.getParameterNames();
            if (joinPoint.getArgs()!=null) {
                String paramStr = "";
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    paramStr = getParamStr(joinPoint.getArgs()[i]);
                    logger.info(paramNames[i] + "=" + paramStr);
                }
            }
        }
    }

    private String getParamStr(Object arg) {
        String paramStr = "";
        if (ObjectUtils.isArray(arg)) {
            Object[] argArray = ObjectUtils.toObjectArray(arg);
            paramStr = JSON.toJSONString(argArray);
        } else {
            paramStr = String.valueOf(arg);
        }
        return paramStr.length()>500?paramStr.substring(0, 500):paramStr;
    }

    @AfterReturning(returning="returnValue", value = "@annotation(top.auntie.annotation.ServiceLog) && @annotation(serviceLog)")
    public void AfterReturning(JoinPoint joinPoint , ServiceLog serviceLog, Object returnValue){
        logger.info("调用 " + serviceLog.value() + "完成");
        logger.info(serviceLog.value()+"返回："+JSON.toJSONString(returnValue));
        if (serviceLog.isOutter()){
            logger.info("===============================");
        }
    }

    @AfterThrowing(value = "@annotation(top.auntie.annotation.ServiceLog) && @annotation(serviceLog)", throwing="ex")
    public void AfterThrowing(JoinPoint joinPoint, ServiceLog serviceLog, Throwable ex){
        logger.error("调用 " + serviceLog.value() + "失败："+ex.getMessage(), ex);
        if (serviceLog.isOutter()){
            logger.info("===============================");
        }
    }

}
