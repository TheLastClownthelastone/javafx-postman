package com.pt.postman.state;

import com.pt.postman.model.request.HTTPConstants;

import java.util.List;

/**
 * @author nate-pt
 * @date 2021/8/12 15:49
 * @Since 1.8
 * @Description 请求信息
 */
public class ComposerState {
    /**
     * 来源
     */
    public String target;
    /**
     * http请求方法
     */
    public String httpMethod;
    /**
     * 认证方法
     */
    public String authMethod;

    /**
     * 请求参数属性信息
     */
    public List<FieldState> params;
    /**
     * 请求头部信息
     */
    public List<FieldState> headers;

    /**
     * Content-Type
     */
    public String contentType;

    /**
     * 请求体为raw选中的值
     */
    public String rawBody;
    /**
     * 请求raw中额内容
     */
    public String rawBodyValue;

    /**
     * HTTP Basic Authentication 认证用户名
     */
    public String basicUserName;

    /**
     * HTTP Basic Authentication 密码
     */
    public String basicPassword;

    /**
     *是否开启 Basic 认证
     */
    public boolean basicEnable;

    /**
     * HTTP Digest 认证用户名
     */
    public String digestUserName;

    /**
     * HTTP Digest 认证密码
     */
    public String digestPassWord;

    /**
     * 是否开启Http Digest认证
     */
    public boolean digestEnable;

    /**
     * url输入框信息
     */
    public List<FieldState> urlStringTuples;

    /**
     * http 表格请求信息 请求头设置为：multipart-form
     */
    public List<FieldState> formStringTuples;

    /**
     * http 表格文件请求信息 请求头设置为 multipart-file
     */
    public List<FieldState> formFileTuples;

    /**
     * 文件下载的时候用到 将contentType 设置为：application/octet-stream
     */
    public String binaryFilePath;


    public ComposerState(){
        this.httpMethod = HTTPConstants.GET;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComposerState state = (ComposerState) o;
        if (!target.equals(state.target)) return false;
        if (!httpMethod.equals(state.httpMethod)) return false;
        if (!authMethod.equals(state.authMethod)) return false;
        if (!params.equals(state.params)) return false;
        if (!headers.equals(state.headers)) return false;
        if (!basicUserName.equals(state.basicUserName)) return false;
        if (!basicPassword.equals(state.basicPassword)) return false;
        if (basicEnable != state.basicEnable) return false;
        if (!digestUserName.equals(state.digestUserName)) return false;
        if (!digestPassWord.equals(state.digestPassWord)) return false;
        if (digestEnable != state.digestEnable) return false;

        if (state.httpMethod.equals(HTTPConstants.GET)
                || state.httpMethod.equals(HTTPConstants.DELETE)) return true;

        if (!contentType.equals(state.contentType)) return false;
        if (!rawBody.equals(state.rawBody)) return false;
        if (!rawBodyValue.equals(state.rawBodyValue)) return false;
        if (!binaryFilePath.equals(state.binaryFilePath)) return false;
        if (!urlStringTuples.equals(state.urlStringTuples)) return false;
        if (!formStringTuples.equals(state.formStringTuples)) return false;
        if (!formFileTuples.equals(state.formFileTuples)) return false;

        return true;
    }
}
