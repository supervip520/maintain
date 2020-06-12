package com.along.entity;

import java.util.List;

import lombok.Data;

@Data
public class DbTree {

	private  Integer id;
    private  Integer pid;
    private  String name;
    private  String icon;
    private  String url;

    private List<DbTree> children;

    
}
