package com.project.bit.userstatus.domain;

import lombok.Data;

@Data
public class Criteria {

    private int pageNum;
    private int amountInPage;

    public Criteria(){
        this(1,15);
    }

    public Criteria(int pageNum, int amountInPage) {
        this.pageNum = pageNum;
        this.amountInPage = amountInPage;
    }

}
