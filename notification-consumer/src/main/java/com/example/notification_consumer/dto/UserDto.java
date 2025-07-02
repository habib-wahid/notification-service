package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto {

    private String userName;
    private String password;
    private Boolean active;
    private String name;
    private String firstName;
    private String lastName;
    private String gender;
    private List<String> emailList;
    private List<String> contactList;

}
