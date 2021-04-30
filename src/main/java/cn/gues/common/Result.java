package cn.gues.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
    private String code;
    private String message;
    private T data;

  /**
   * @description 返回成功自定义参数
   * 
   * @param code 1
   * @param message 2
   * @param data 3
   * @return Result<T>
   * @author zhangbin
   * @date 2021/4/26 13:42
   */
    public static <T> Result<T> succeedWith(String code,String message,T data){
        return new Result<>(code,message,data);
    }

    /**
     * @description 返回成功（带消息）
     * 
     * @param message 1
     * @return Result<T>
     * @author zhangbin
     * @date 2021/4/26 13:42
     */
    public static <T> Result<T> succeed(String message){
        return succeedWith("200",message,null);
    }

    /**
     * @description 返回成功（带数据）
     * 
     * @param data 1
     * @return Result<T>
     * @author zhangbin
     * @date 2021/4/26 13:42
     */
    public static <T> Result<T> succeed(T data){
        return succeedWith("200","成功",data);
    }

   /**
    * @description 返回成功（带消息和数据）
    * 
    * @param message 1
    * @param data 2
    * @return Result<T>
    * @author zhangbin
    * @date 2021/4/26 13:43
    */
    public static <T> Result<T> succeed(String message,T data){
        return succeedWith("200",message,data);
    }
    /**
     * @description 返回失败（自定义参数）
     *
     * @param code 1
     * @param message 2
     * @param data 3
     * @return Result<T>
     * @author zhangbin
     * @date 2021/4/26 13:43
     */
    public static <T> Result<T> failedWith(String code,String message,T data){
        return new Result<>(code,message,data);
    }

   /**
    * @description 返回失败（带消息）
    *
    * @param message 1
    * @return Result<T>
    * @author zhangbin
    * @date 2021/4/26 13:43
    */
    public static <T> Result<T> failed(String message){
        return succeedWith("202",message,null);
    }

    /**
     * @description 返回失败（带数据）
     *
     * @param
     * @return Result<T>
     * @author zhangbin
     * @date 2021/4/26 13:43
     */
    public static <T> Result<T> failed(){
        return succeedWith("202","未知错误",null);
    }

}
