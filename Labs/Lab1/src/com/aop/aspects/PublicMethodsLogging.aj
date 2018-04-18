package com.aop.aspects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public aspect PublicMethodsLogging {

    private pointcut publicMethodsTracing():execution(public * *(..)) && ! within(PublicMethodsLogging) ;//&& !execution(*Object+.toString());

    before():publicMethodsTracing() {
        Logger logger = LogManager.getLogger("Public Controller Methods");

        //String arguments = thisJoinPoint.getArgs().toString();
        logger.info(" Entering " + thisJoinPointStaticPart.getSignature() /*+ arguments*/);

    }
    after(): publicMethodsTracing() {
        Logger logger = LogManager.getLogger("Public Controller Methods");

        String arguments = thisJoinPoint.getArgs().toString();
        logger.info(" Exiting" + thisJoinPointStaticPart.getSignature() + arguments);


    }



}
