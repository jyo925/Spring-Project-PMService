package com.project.bit.approval.controller;

import com.project.bit.approval.domain.ApFileDTO;
import lombok.extern.java.Log;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log
@Controller
@RequestMapping("approval")
public class ApprovalFileController {

    // 년/월/일 폴더 만들기
    public String getFolder() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }

    //첨부파일 삭제
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type){

        log.info(
                "deleteFile: "+ fileName);
        File file;
        try {
            //디코딩 = 바이트 형식을 문자로 변환
            file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));

            file.delete();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }


    // 업로드 파일을 c드라이브에 저장
    @PostMapping("/uploadFormAction")
    public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

        String uploadFoder = "C:\\upload";
        for (MultipartFile multipartFile : uploadFile) {
            log.info("--------------------------------------");
            log.info("Upload File Name: " + multipartFile.getOriginalFilename());
            log.info("Upload File Size: " + multipartFile.getSize());

            File saveFile = new File(uploadFoder, multipartFile.getOriginalFilename()); // 경로, 파일명 지정
            try {
                multipartFile.transferTo(saveFile);// 저장
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        } // end for
    }


    // 파일 다운로드
    // IE도 서비스하는 경우 HttpServletRequest에 포함된 헤더 정보를 이용해서 브라우저 종류 확인
    // User-Agent 정보를 수집해서 IE처리
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent, String fileName){

		log.info("download file: "+ fileName);
        Resource resource = new FileSystemResource("C:\\upload\\" + fileName);

        if(resource.exists() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String resourceName = resource.getFilename();

        // remove UUID
        String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

        HttpHeaders headers = new HttpHeaders();

        try {
            String downloadName = null;

            //IE 브라우저 엔진 이름
            if(userAgent.contains("Trident")) {
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
            }else if(userAgent.contains("Edge")){
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
            }else {
                log.info("Chrome browser");
                downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            }

            log.info("downloadName: "+ downloadName);

            //한글파일 깨짐 처리
            headers.add("Content-Disposition", "attachment; filename="+downloadName);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    //파일 서버에 등록 및 등록결과 뷰에 반영
//    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ApFileDTO>> uploadAjaxPost(MultipartFile[] apFiles) {

        log.info("update ajax post......");

        List<ApFileDTO> list = new ArrayList<>();

        String uploadFolder = "C:\\upload";
        String uploadFolderPath = getFolder();

        // 폴더 생성
        File uploadPath = new File(uploadFolder, uploadFolderPath);

        // 해당 경로가 있는지 검사하고 없으면 mkdirs 명령을 통해 생성
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : apFiles) {
//			log.info("Upload File Name: "+ multipartFile.getOriginalFilename());
//			log.info("Upload File Size: " + multipartFile.getSize());
            ApFileDTO apFileDTO = new ApFileDTO();

            String uploadFileName = multipartFile.getOriginalFilename();

            // IE has file path
            // IE의 경우 전체 파일 경로가 전송되므로 마지막 '\'를 기준으로 잘라낸 문자열이 실제 파일 이름이 됨
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

            log.info("only file name: " + uploadFileName);
            apFileDTO.setApFileName(uploadFileName);

            // 파일 중복 저장 방지
            UUID uuid = UUID.randomUUID();

            uploadFileName = uuid.toString() + "_" + uploadFileName;

            try {
                File saveFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveFile);// 저장

                apFileDTO.setApFileUuid(uuid.toString());
                apFileDTO.setApFilePath(uploadFolderPath);

                // add to List
                list.add(apFileDTO);

            } catch (Exception e) {
                log.info(e.getMessage());
            } // end catch
        } // end for

        return new ResponseEntity<>(list, HttpStatus.OK);
    }




}
