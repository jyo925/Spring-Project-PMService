package com.project.bit.foo.service.myFiles;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bit.foo.domain.files.FilesVO;
import com.project.bit.foo.mapper.MyFilesMapper;
import com.project.bit.project.domain.ProjectOutputDTO;

@Service
public class MyFilesServiceImpl implements MyFilesService {

	private final MyFilesMapper myFilesMapper;
	
	public MyFilesServiceImpl(MyFilesMapper myFilesMapper) {
		this.myFilesMapper = myFilesMapper;
	}

	@Override
	public List<FilesVO> getMyFiles(String userId) {
		return myFilesMapper.selectMyFiles(userId);
	}

	@Override
	public void editFile(String outputCode, ProjectOutputDTO fileOutput) {
		myFilesMapper.updateFile(outputCode, fileOutput);
		
	}

	@Override
	public void removeFile(String fileId) {
		myFilesMapper.deleteFile(fileId);
		
	}

	@Override
	public FilesVO findFile(String fileId) {
		// TODO Auto-generated method stub
		return myFilesMapper.selectFile(fileId);
	}

}