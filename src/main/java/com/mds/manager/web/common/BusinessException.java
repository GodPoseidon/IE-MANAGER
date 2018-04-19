package com.mds.manager.web.common;


import java.io.Serializable;

/**
 * 业务异常统一异常类
 */
public class BusinessException extends Exception {

    /**
     * Comment for <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 6781411320173267794L;

    /**
     * 错误代码.
     */
    private String errorCode;

    /**
     * 异常参数
     */
    private Serializable[] attachements;

    /**
     * 业务异常构造函数 构建一个<code>BusinessException.java</code>
     * 
     * @param errorCode
     *            业务异常代码
     */
    public BusinessException(final String errorCode) {
        this(errorCode, (Serializable[])null);
    }

    /**
     * 业务异常构造函数 构建一个<code>BusinessException.java</code>
     * 
     * @param errorCode
     *            业务异常代码
     * @param attachements
     *            异常参数
     */
    public BusinessException(final String errorCode, Serializable... attachements) {
        super(errorCode);
        this.errorCode = errorCode;
        this.attachements = attachements;
    }

    /**
     * 取得业务异常代码.
     * 
     * @return 业务异常代码
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 取得错误参数
     * 
     * @return 错误参数数组
     */
    public Serializable[] getAttachements() {
        return this.attachements;
    }

}
