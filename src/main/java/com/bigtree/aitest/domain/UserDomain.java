package com.bigtree.aitest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDomain implements Serializable {
    private int id;
    private String name;
    private String phone;
}