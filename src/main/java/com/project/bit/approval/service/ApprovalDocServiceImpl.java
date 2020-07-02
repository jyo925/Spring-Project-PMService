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
    public void postApDocTerm(ApDateDTO apDateDTO) {
        apDocMapper.insertApDocTerm(apDateDTO);
    }

    @Override
    public ApDateDTO getApDocTerm(String apDocNo) {

        ApDateDTO apDateDTO = apDocMapper.selectApDocTerm(apDocNo);
        apDateDTO.setApStartDate(apDateDTO.getApStartDate().substring(0,10));
        apDateDTO.setApEndDate(apDateDTO.getApEndDate().substring(0,10));

        return apDateDTO;
    }

    @Override
    public Long getNewApDocNo(ApDocDTO apDocDTO) {
        return apDocMapper.selectNewApDocNo(apDocDTO);
    }

    @Override
    public void removeApDoc(String apDocNo, String apDocWriter) {
        apDocMapper.deleteApDoc(apDocNo, apDocWriter);
    }

    @Override
    public List<ApDocDTO> getApProgressList(String apDocWriter, Criteria cri) {
        return apDocMapper.selectApProgressList(apDocWriter, cri);
    }

    @Override
    public List<ApDocDTO> getApCompleteList(String userId, Criteria cri) {
        return apDocMapper.selectApCompleteList(userId, cri);
    }

    @Override
    public List<Integer> getApDocCount(String apDocWriter) {

        List<Integer> apDocCountList = new ArrayList<Integer>();
        
        //결재 진행(진행중, 반려) 개수
        apDocCountList.add(apDocMapper.selectCountApDoc(0, apDocWriter) +
                apDocMapper.selectCountApDoc(2, apDocWriter));
        //결재 대기 개수
        apDocCountList.add(apDocMapper.selectCountApCheck(apDocWriter));

        return apDocCountList;
    }


    @Override
    public List<ApDocDTO> getApCheckList(String apDocWriter, Criteria cri) {
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
        if(apDTO.getApResult()=='1'){
            //승인시 --->문서단계(+1) 업데이트 &  & 다음결재자 결재수신일자 업데이트
            apDocMapper.updateApDocStep(apDTO.getApDocNo());
            apMapper.updateNextApReceiveDate(apDTO);
        }else {
            //반려시 ---> 상태 2(반려)로, 단계 -1로
            apDocMapper.updateApDocReject(apDTO.getApDocNo());
        }
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

        String[] apReferrers = apReferrersId.split(",");

        for(int i=0; i<apReferrers.length; i++){
            apReferrerMapper.insertApDocReferrer(apDocNo, apReferrers[i]);
        }
    }

    @Override
    public List<ApDocDTO> getApReferList(String apReferrer, Criteria cri) {

        return apDocMapper.selectApReferList(apReferrer, cri);
    }

    @Override
    public int getApReferDocCount(String apReferrer) {
        return apDocMapper.selectCountApRefer(apReferrer);
    }

    @Override
    public int getApCompleteDocCount(String apDocWriter) {
        return apDocMapper.selectCountApDoc(1,apDocWriter);
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

    @Override
    public ApDocWriterVO getApDocWriterInfo(String userId) {
        return apDocMapper.selectApDocWriterInfo(userId);
    }
}
