package com.wulang;

import static org.junit.Assert.assertTrue;

import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.filter.expression.NowExpression;
import org.junit.Test;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        System.out.println(System.getenv("ROCKETMQ_HOME"));
    }
    @Test
    public void timeTest()
    {
        Date now=new Date();
        System.out.println(now);
        String a=UtilAll.timeMillisToHumanString3(now.getTime());
        System.out.println(a);
    }
}
