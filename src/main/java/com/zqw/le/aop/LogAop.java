package com.zqw.le.aop;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.zqw.le.domain.p.LogEntity;
import com.zqw.le.domain.p.LogRespository;

@Aspect
@Component
public class LogAop {
	@Autowired
	private LogRespository LogRespository;
	
	@Pointcut(("@annotation(com.zqw.le.aop.Log)"))
	public void log(){
		
	}
	/*@After("log()")
    public void afterExec(JoinPoint joinPoint){
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method method=ms.getMethod();
        System.out.println(11111);
    }*/
	@Around("log()")
	public Object aroundExec(ProceedingJoinPoint point) throws Throwable{
		String className = point.getThis().getClass().getName(); // 类名
		if (className.indexOf("$$EnhancerByCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
			try {
				className = className.substring(0, className.indexOf("$$"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		String methodName = className + "." + point.getSignature().getName(); // 获取方法名

		Object[] methodParam = null; // 参数
		Object object;
		String params = "";
		try {
			methodParam = point.getArgs(); // 获取方法参数

			// 判断参数类型
			/*if (methodParam[0] instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, Object> paramsMap = this.getParameterMap((Map<String, Object>) methodParam[0]);
				params = this.mapToString(paramsMap);
			} else if (methodParam[0] instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) methodParam[0];

				String appParams = request.getParameter("paramsJson"); // app端传来的参数

				if (!(null==appParams||"".equals(appParams.trim()))
						&& !(appParams==null||appParams.length()==0)) {
					//如果是APP端操作，修改编码
					String udStr = URLDecoder.decode(appParams, "UTF-8");
					params = udStr;
				} else {
					params = this.getParams(request);
				}
			}*/

			object = point.proceed();
		} catch (Exception e) {
			// 异常处理记录日志..log.error(e);
			throw e;
		}

		Map<String, String> methodMap = getMethodRemark(point);

		String methodRemark = ""; // 操作备注
		String operateType = ""; // 操作类型
		String funModule = ""; // 功能模块
		if (methodMap != null) {
			funModule = methodMap.get("name");
			methodRemark = methodMap.get("remark"); // 获取操作备注
			operateType = methodMap.get("operateType"); // 获取操作类型
		}

		String operateRemark = methodRemark; // 操作备注

		LogEntity loge = new LogEntity();
		loge.setName(funModule);
		loge.setRemark(methodRemark);
		loge.setOperateType(operateType);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		loge.setTime(now);

		LogRespository.save(loge);

		return object;
	}
	
	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getParameterMap(Map map) {
		// 返回值Map
		Map<String, String> returnMap = new HashMap<String, String>();
		Iterator<?> entries = map.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}

			if (valueObj != null && !(null==value||"".equals(value.trim())) && !name.equals("_") && name != "_") {
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}
	
	/**
	 * 将map转为String
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToString(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		List<String> keys = new ArrayList<String>(map.keySet());

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key).toString();
			sb.append(key + "=" + value);
			sb.append(",");
		}
		String s = sb.toString();
		if (s.endsWith(",")) {
			s = s.substring(0, s.lastIndexOf(","));
		}
		return s;
	}
	
	/**
	 * 获取参数
	 * 
	 * @param httpServletRequest
	 */
	@SuppressWarnings("rawtypes")
	public String getParams(HttpServletRequest httpServletRequest) {
		Map paramsMap = new HashMap();
		boolean isAjaxCall = this.isAjaxCall(httpServletRequest);
		if (isAjaxCall) {
			paramsMap = WebUtils.getParametersStartingWith(httpServletRequest, "");
			paramsMap = getParameterMap(paramsMap);
		} else {
			paramsMap = getParameterMap((httpServletRequest.getParameterMap()));
		}
		@SuppressWarnings("unchecked")
		String params = mapToString(paramsMap);
		return params;
	}
	
	public boolean isAjaxCall(HttpServletRequest request) {
		return ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
	}
	
	/**
	 * @Description 获取方法的中文备注，用于记录用户的操作日志描述
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getMethodRemark(JoinPoint joinPoint)
			throws Exception {
		Map<String, String> methodMap = new HashMap<String, String>();

		Class<?> targetClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		Object[] parameterTypes = joinPoint.getArgs();

		for (Method method : targetClass.getDeclaredMethods()) {
			if (method.getName().equals(methodName)
					&& method.getParameterTypes().length == parameterTypes.length) {
				Log methodLog = method.getAnnotation(Log.class);
				if (methodLog != null) {
					methodMap.put("name", methodLog.name());
					methodMap.put("remark", methodLog.remark());
					methodMap.put("operateType", methodLog.operateType().toString());
					return methodMap;
				}
				break;
			}
		}

		return null;
	}
}
