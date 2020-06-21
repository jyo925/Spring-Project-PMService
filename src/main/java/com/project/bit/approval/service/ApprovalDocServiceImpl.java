package com.project.bit.approval.service;

import com.project.bit.approval.domain.*;
import com.project.bit.approval.mapper.ApDocMapper;
import com.project.bit.approval.mapper.ApFileMapper;
import com.project.bit.approval.mapper.ApMapper;
import com.project.bit.approval.mapper.ApReferrerMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class ApprovalDocServiceImpl implements ApprovalDocService {

    @Autowired
    ApDocMapper apDocMapper;

    @Autowired
    ApMapper apMapper;

    @Autowired
    ApFileMapper apFileMapper;

    @Autowired
    ApReferrerMapper apReferrerMapper;

    @Override
    public ApFormDTO getApForm(String apFormNo) {
        return apDocMapper.selectApForm(apFormNo);
    }

    @Override
    public int postApDoc(ApDocDTO apDocDTO) {
        return apDocMapper.insertApDoc(apDocDTO);
    }

    @Override
    public Long getNewApDocNo(ApDocDTO apDocDTO) {
        return apDocMapper.selectNewApDocNo(apDocDTO);
    }

    //진행문서함
    @Override
    public List<ApDocListVO> getApProgressList(String apDocWriter, Criteria cri) {
        return apDocMapper.selectApProgressList(apDocWriter, cri);
    }


    @Override
    public List<Integer> getApDocCount(String apDocWriter, Criteria cri) {

        List<Integer> apDocCountList = new ArrayList<Integer>();
        //결재진행(진행중, 반려) 개수
        apDocCountList.add(apDocMapper.selectCountApDoc(0, apDocWriter, cri) +
                apDocMapper.selectCountApDoc(2, apDocWriter, cri));
        //결재대기 개수
        apDocCountList.add(apDocMapper.selectCountApCheck(apDocWriter));
        //임시저장 개수
        apDocCountList.add(apDocMapper.selectCountApDoc(3, apDocWriter, cri));

        return apDocCountList;
    }

    //결재 대기 문서함
    @Override
    public List<ApDocListVO> getApCheckList(String apDocWriter, Criteria cri) {
        return apDocMapper.selectApCheckList(apDocWriter, cri);
    }

    @Override
    public int postApDocFiles(ApFileDTO apFileDTO) {
        long apDocNo = apFileDTO.getApDocNo();
        String[] apFileName = apFileDTO.getApFileName().split("[*]b[*]l[*]a[*]b[*]l[*]a[*],*");
        String[] apFilePath = apFileDTO.getApFilePath().split(",");
        String[] apFileUuid = apFileDTO.getApFileUuid().split(",");
        String[] apFileType = apFileDTO.getApFileType().split(",");

        for (int i = 0; i < apFileName.length; i++) {
            apFileMapper.insertApFile(ApFileDTO.builder()
                    .apDocNo(apDocNo)
                    .apFileName(apFileName[i])
                    .apFileUuid(apFileUuid[i])
                    .apFilePath(apFilePath[i])
                    .apFileType(apFileType[i]).build());
        }

        return 0;
    }

    @Override
    public List<ReferrerVO> getReferrerUserList() {
        return apReferrerMapper.selectReferrerUserList();
    }

    @Override
    public ApDocDTO getApDoc(String apDocNo) {

        return apDocMapper.selectApDoc(apDocNo);
    }

    @Override
    public List<String> getApDocViewableUsers(String apDocNo) {
        return apDocMapper.selectApDocViewableUsers(apDocNo);
    }

    @Override
    public void putApDoc(ApDTO apDTO) {
        //문서단계(+1) 업데이트 &  & 다음결재자 결재수신일자 업데이트
        apDocMapper.updateApDocStep(apDTO.getApDocNo());
        apMapper.updateNextApReceiveDate(apDTO);
    }

    @Override
    public void putLastApDoc(long apDocNo) {
        apDocMapper.updateLastApDoc(apDocNo);
//        문서상태를 완료(1로), 완료일자 업데이트
    }

    @Override
    public List<ApFileDTO> getApFiles(String apDocNo) {
        return apFileMapper.selectApFiles(apDocNo);
    }

    @Override
    public void postApDocReferrers(long apDocNo, String apReferrersId) {
        log.info("서비스단 ------------------------------------------------");
        log.info(apReferrersId);

        String[] apReferrers = apReferrersId.split(",");
        log.info(apReferrers+"");

        for(int i=0; i<apReferrers.length; i++){
            apReferrerMapper.insertApDocReferrer(apDocNo, apReferrers[i]);
        }
    }

    @Override
    public List<ApDocListVO> getApReferList(String apReferrer, Criteria cri) {

        return apDocMapper.selectApReferList(apReferrer, cri);
    }

    @Override
    public int getApReferDocCount(String apReferrer) {
        return apDocMapper.selectCountApRefer(apReferrer);
    }

    @Override
    public String getApDocReferrers(String apDocNo) {
        List<ReferrerVO> apReferrers = apReferrerMapper.selectApDocReferrers(apDocNo);
        String apReferrersStr = "";
        if(apReferrers.size()!=0){
            for(int i=0; i<apReferrers.size(); i++){
                apReferrersStr += apReferrers.get(i).getUserName() +" "+apReferrers.get(i).getPositionName()+", ";
            }
            apReferrersStr = apReferrersStr.substring(0, apReferrersStr.length()-2);
        }
        return apReferrersStr;
    }
}
