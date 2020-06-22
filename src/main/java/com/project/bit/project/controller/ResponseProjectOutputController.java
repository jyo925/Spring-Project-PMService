package com.project.bit.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.service.ProjectOutputService;

@RestController
@RequestMapping("/output")
public class ResponseProjectOutputController {
	
	@Autowired
	private ProjectOutputService projectOutputService;

	// 년/월/일 폴더 만들기
	public String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public void postProjectOutput(MultipartFile[] task_output, String[] outputTypeCode, String[] taskCode,
			Principal principal) {

		int size = task_output.length;
		List<ProjectOutputDTO> outputs = new ArrayList<ProjectOutputDTO>();

		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();

		File uploadPath = new File(uploadFolder, uploadFolderPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (int i = 0; i < size; i++) {
			ProjectOutputDTO output = new ProjectOutputDTO();

			UUID uuid = UUID.randomUUID();
			String fileName = task_output[i].getOriginalFilename();
			//fileName = uuid.toString() + "_" + fileName;

			output.setOutputName(task_output[i].getOriginalFilename());
			output.setOutputUser(principal.getName());
			output.setOutputPath(uploadPath.getPath() + "\\" + fileName);
			output.setOutputTypeCode(outputTypeCode[i]);
			output.setTaskCode(taskCode[i]);

			try {
				File saveFile = new File(uploadPath, output.getOutputName());
				task_output[i].transferTo(saveFile);

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			outputs.add(output);
		}
		
		for(ProjectOutputDTO output : outputs) {
			projectOutputService.postProjectOutput(output);
		}

		// return "";
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadProjectOutput(String outputName, String outputPath) {
		Resource resource = new FileSystemResource(outputPath);
		HttpHeaders headers = new HttpHeaders();
		
		String filename;
		try {
			filename = new String(resource.getFilename().getBytes("UTF-8"), "ISO-8859-1");
			headers.add("Content-Disposition", "attachment; filename=" + filename);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{outputId}")
	public void removeProjectOutput(@PathVariable String outputId, String outputName, String outputPath) {
		System.err.println(outputId + "  " + outputName + "  " + outputPath);
		
		projectOutputService.removeProjectOutput(outputId);
		
		File file = new File(outputPath);
		file.delete();
	}
}
