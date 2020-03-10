package com.imooc.sell.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author CodeMonkey
 * @date 2020/2/20 14:26
 */
@Data
public class ResultVO<T> {

    /**错误码*/
    private Integer code;

    /**提示信息*/
    private String msg;

    /**返回的具体内容*/
    private T data;

}
