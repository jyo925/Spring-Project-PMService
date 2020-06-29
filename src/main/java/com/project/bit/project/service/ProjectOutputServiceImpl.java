package com.project.bit.project.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.domain.ProjectOutputTypeDTO;
import com.project.bit.project.mapper.ProjectOutputMapper;

@Service
public class ProjectOutputServiceImpl implements ProjectOutputService {

	@Autowired
	private ProjectOutputMapper projectOutputMapper;
	private static final String uploadDirectory = System.getProperty("user.dir") + "\\uploads";

	@Override
	public List<ProjectOutputDTO> getProjectDetailOutput(String projectCode) {
		// TODO Auto-generated method stub
		return projectOutputMapper.selectProjectDetailOutput(projectCode);
	}

	@Override
	public List<ProjectOutputTypeDTO> getOutputType() {
		// TODO Auto-generated method stub
		return projectOutputMapper.selectProjectOutputType();
	}

	@Override
	public List<ProjectOutputDTO> getProjectOutput() {
		// TODO Auto-generated method stub
		return projectOutputMapper.selectProjectOutput();
	}

	@Override
	public void postProjectOutput(ProjectOutputDTO projectOutputDTO) {
		String uploadFolder = uploadDirectory;
		String uploadFolderPath = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()).replace("-", File.separator);
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		projectOutputDTO.setOutputPath(uploadPath.getPath() + "\\" + projectOutputDTO.getOutputName());
		projectOutputMapper.insertProjectOutput(projectOutputDTO);

	}

	@Override
	public void removeProjectOutput(String outputId) {
		projectOutputMapper.deleteProjectOutput(outputId);

	}

	@Override
	public void postProjectOutput(MultipartFile outputFile) {
		if(outputFile != null) {
			
			String uploadFolder = uploadDirectory;
			String uploadFolderPath = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()).replace("-", File.separator);
			
			File uploadPath = new File(uploadFolder, uploadFolderPath);
			
			if (uploadPath.exists() == false) {
				uploadPath.mkdirs();
			}
			
			try {
				File saveFile = new File(uploadPath, outputFile.getOriginalFilename());
				outputFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//projectOutputMapper.update
			
			
		} else {
			
		}
		

	}

	@Override
	public void putProjectOutput(ProjectOutputDTO projectOutputDTO) {
		String num = projectOutputDTO.getOutputPath();
		
		String uploadFolder = uploadDirectory;
		String uploadFolderPath = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()).replace("-", File.separator);
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(num.equals("1")) {
			projectOutputDTO.setOutputPath(uploadPath.getPath() + "\\" + projectOutputDTO.getOutputName());
		}
		
		projectOutputMapper.updateProjectOutput(projectOutputDTO);
	}

	@Override
	public List<ProjectOutputDTO> getProjectOutputByCategory(String projectCode, String typeCode) {
		return projectOutputMapper.selectProjectOutputByType(projectCode, typeCode);
	}
}
