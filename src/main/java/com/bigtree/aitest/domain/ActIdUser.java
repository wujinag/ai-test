package com.bigtree.aitest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description act_id_user
 * @author zhengkai.blog.csdn.net
 * @date 2023-10-10
 */
@Data
public class ActIdUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * id_
    */
    private String id_;

    /**
    * rev_
    */
    private Integer rev_;

    /**
    * first_
    */
    private String first_;

    /**
    * last_
    */
    private String last_;

    /**
    * email_
    */
    private String email_;

    /**
    * pwd_
    */
    private String pwd_;

    /**
    * salt_
    */
    private String salt_;

    /**
    * lock_exp_time_
    */
    private Date lock_exp_time_;

    /**
    * attempts_
    */
    private Integer attempts_;

    /**
    * picture_id_
    */
    private String picture_Id_;

    public ActIdUser() {}
}