package com.project.bit.approval.domain;

import lombok.Data;

@Data
public class ApFileDTO {

    private long apFileNo;
    private String apFileName;
    private String apFilePath;
    private String apFileType;
    private String apFileUuid;

    private long apDocNo;

}
