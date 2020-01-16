package com.oyxt.example.mode;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 20190712713
 * @date 2020/1/16 9:46
 */
@Data
public class Mission implements Serializable {
    private static final long serialVersionUID = -7307049478976069648L;

    private String mission;
    private String taskId;
}
