package com.project.bit.dashBoard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class IssueTypeCountVO {
    private String issueTypeName;
    private int issueTypeAll;
    private int issueTypeDelay;
    private int issueTypeChange;
    private int issueTypeImprove;
    private int issueTypeEtc;
}