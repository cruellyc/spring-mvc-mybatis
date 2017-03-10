package com.doorlock.dto;

import java.io.Serializable;

/**
 * 映射结果定义
 * @author  liyc
 * @date 2017年3月10日 下午2:03:04
*/
public class DemoResult  implements Serializable{
    /**
     * Comment for <code>serialVersionUID</code><br>
     * 
     */
    private static final long serialVersionUID = -413001138792531448L;
    private long sum;

    public long getSum()
    {
        return sum;
    }

    public void setSum(long sum)
    {
        this.sum = sum;
    }

    @Override
    public String toString()
    {
        return String.valueOf(sum);
    }
}
