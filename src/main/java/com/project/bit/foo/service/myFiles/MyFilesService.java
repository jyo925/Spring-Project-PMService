package com.project.bit.foo.service.myFiles;

import java.util.List;

import com.project.bit.foo.domain.files.FilesVO;
import com.project.bit.project.domain.ProjectOutputDTO;

public interface MyFilesService {
	
	List<FilesVO> getMyFiles(String userId);

	void editFile(String outputCode, ProjectOutputDTO fileOutput);

	void removeFile(String fileId);

	FilesVO findFile(String fileId);

}
