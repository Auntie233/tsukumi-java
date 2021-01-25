package top.auntie.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPassDto implements Serializable {

    private String username;

    private String password;

}
