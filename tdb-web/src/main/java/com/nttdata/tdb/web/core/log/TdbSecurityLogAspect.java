package com.nttdata.tdb.web.core.log;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author jean.lorenzini
 *
 */
public class TdbSecurityLogAspect {

	public Object logAroundUpload(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = null;
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : "+ Arrays.toString(joinPoint.getArgs()));
        try {
            returnValue = joinPoint.proceed();
            if (returnValue != null) {
            	System.out.println(returnValue.toString());
            }
            return returnValue;
        } catch (Exception e) {
            throw e;
        } finally {
        	System.out.println("Upload Successful");
        }
    }




}
