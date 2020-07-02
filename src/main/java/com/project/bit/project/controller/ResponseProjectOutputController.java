package com.project.bit.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

	private static final String uploadDirectory = System.getProperty("user.dir") + "\\uploads";

	// 년/월/일 폴더 만들기
	public String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	@PutMapping("/update")
	public void putProjectOutput(ProjectOutputDTO projectOutputDTO) {
		projectOutputService.putProjectOutput(projectOutputDTO);
	}

	@DeleteMapping("/delete/{outputCode}")
	public void removeProjectOutput(@PathVariable String outputCode) {
		projectOutputService.removeProjectOutput(outputCode);
	}

	@PostMapping(value = "/file/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public void postProjectOutput(MultipartFile outputFile) {
		projectOutputService.postProjectOutput(outputFile);
	}

	@PostMapping("/add")
	public void postProjectOutput(ProjectOutputDTO projectOutputDTO) {
		System.err.println(projectOutputDTO);
		projectOutputService.postProjectOutput(projectOutputDTO);
	}

	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public void postProjectOutput(MultipartFile[] task_output, String[] outputTypeCode, String[] taskCode,
			Principal principal) {

		int size = task_output.length;
		List<ProjectOutputDTO> outputs = new ArrayList<ProjectOutputDTO>();

		String uploadFolder = uploadDirectory;
		String uploadFolderPath = getFolder();

		File uploadPath = new File(uploadFolder, uploadFolderPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (int i = 0; i < size; i++) {
			ProjectOutputDTO output = new ProjectOutputDTO();
			String fileName = task_output[i].getOriginalFilename();

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

		for (ProjectOutputDTO output : outputs) {
			projectOutputService.postProjectOutput(output);
		}

		// return "redirect:/projectIssueList";
	}

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadProjectOutput(@RequestHeader("User-Agent") String userAgent,
			String fileName) {

		Resource resource = new FileSystemResource(fileName);
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();

		String downloadName = null;
		
		try {
			downloadName = new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
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
	
	// 프로젝트 산출물 카테고리 검색
	@GetMapping("/search/category/{projectCode}/{typeCode}")
	public List<ProjectOutputDTO> getProjectOutputByCategory(@PathVariable("projectCode") String projectCode, @PathVariable("typeCode") String typeCode, Model model) {
		//model.addAttribute("outputList", projectOutputService.getProjectOutputByCategory(typeCode));
		return projectOutputService.getProjectOutputByCategory(projectCode, typeCode);
	}
}
