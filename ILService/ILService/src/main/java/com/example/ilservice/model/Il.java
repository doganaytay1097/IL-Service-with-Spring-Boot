package com.example.ilservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "iller")
public class Il {

    @Id
    private String id;

    private Date createDate = new Date();

    private String name;

    private int plaka;

}
