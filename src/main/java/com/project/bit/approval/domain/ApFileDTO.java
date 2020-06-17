package com.project.bit.approval.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApFileDTO {

    private long apFileNo;
    private String apFileName;
    private String apFilePath;
    private String apFileType;
    private String apFileUuid;

    private long apDocNo;

}
