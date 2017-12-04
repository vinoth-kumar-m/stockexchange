
package com.dtcc.stockexchange.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * LoggerAspect.java - this class logs all the method calls happening in the application 
 * @author Vinoth
 *
 */
@Aspect
@Component("loggerAspect")
public class LoggerAspect {
	private Logger log = Logger.getLogger(getClass());

	/**
	 * This method implemented with around advice to log start and end of all the methods that matches the pointcut specified 
	 * @param joinPoint A variable of type ProceedingJoingPoint
	 * @return value return from the calling method
	 * @throws exception thrown from the calling method
	 */
	@Around("execution(* com.dtcc.stockexchange..*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info(joinPoint.getTarget().getClass() + " -> " + joinPoint.getSignature().getName() + " -> Start: "+ System.currentTimeMillis());
		Object retVal = null;
		try {
			retVal= joinPoint.proceed();
		} catch(Exception ex) {
			log.error(joinPoint.getTarget().getClass() + " -> " + joinPoint.getSignature().getName() + " -> Exception: " + ex.getMessage());
			throw ex;
		}
		log.info(joinPoint.getTarget().getClass() + " -> " + joinPoint.getSignature().getName() + " -> End: "+ System.currentTimeMillis());
		return retVal;
	}
}
