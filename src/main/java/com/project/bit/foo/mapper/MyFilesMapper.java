package com.project.bit.foo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bit.foo.domain.files.FilesVO;
import com.project.bit.project.domain.ProjectOutputDTO;

@Mapper
public interface MyFilesMapper {
	
	List<FilesVO> selectMyFiles(String userId);

	void updateFile(@RequestParam String outputCode, ProjectOutputDTO fileOutput);

	void deleteFile(String fileId);

	FilesVO selectFile(String fileId);

}
