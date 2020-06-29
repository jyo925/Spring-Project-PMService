package com.project.bit.foo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.bit.foo.domain.Users;
import com.project.bit.foo.domain.files.FilesVO;
import com.project.bit.foo.service.myFiles.MyFilesService;
import com.project.bit.foo.service.tasks.TasksService;
import com.project.bit.project.controller.ResponseProjectOutputController;
import com.project.bit.project.domain.ProjectOutputDTO;
import com.project.bit.project.service.ProjectOutputService;

@Controller
public class FilesIOController {

	private static final String uploadDirectory = System.getProperty("user.dir") + "\\uploads";

	private final TasksService tasksService;
	private final ProjectOutputService projectOutputService;
	private final MyFilesService myFilesService;

	@Autowired
	private ResponseProjectOutputController rpoController;

	public FilesIOController(ProjectOutputService projectOutputService, TasksService tasksService,
			MyFilesService myFilesService) {
		this.projectOutputService = projectOutputService;
		this.tasksService = tasksService;
		this.myFilesService = myFilesService;
	}

	@GetMapping("/files")
	public String filesIO(Model model, Principal principal) {
		model.addAttribute("types", projectOutputService.getOutputType());
		model.addAttribute("tasks", tasksService.getTasks());
		model.addAttribute("myFiles", myFilesService.getMyFiles(principal.getName()));
		return "filesIO/filesIO";
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("files") MultipartFile[] files, Principal principal,
			ProjectOutputDTO fileOutput) {

		String uploadFolder = uploadDirectory;
		String uploadFolderPath = rpoController.getFolder();

		File uploadPath = new File(uploadFolder, uploadFolderPath);
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadFolder, uploadFolderPath, fileName);
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileOutput.setOutputName(fileName);
			fileOutput.setOutputUser(principal.getName());
			fileOutput.setOutputPath(uploadPath.getPath() + "\\" + fileName);

			projectOutputService.postProjectOutput(fileOutput);

		}
		return "redirect:/files";
	}

	@PostMapping("/editFile")
	public String editFile(@RequestParam("outputCode") String outputCode, ProjectOutputDTO fileOutput) {
		myFilesService.editFile(outputCode, fileOutput);
		return "redirect:/files";
	}

	@RequestMapping("/file/{fileName}")
	@ResponseBody
	public void show(@PathVariable("fileName") String fileName, HttpServletResponse response, Principal principal) {

		if (fileName.indexOf(".doc") > -1) response.setContentType("application/msword");
		if (fileName.indexOf(".docx") > -1) response.setContentType("application/msword");
		if (fileName.indexOf(".xls") > -1) response.setContentType("application/vnd.ms-excel");
		if (fileName.indexOf(".csv") > -1) response.setContentType("application/vnd.ms-excel");
		if (fileName.indexOf(".ppt") > -1) response.setContentType("application/ppt");
		if (fileName.indexOf(".pdf") > -1) response.setContentType("application/pdf");
		if (fileName.indexOf(".zip") > -1) response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setHeader("Content-Transfer-Encoding", "binary");

		List<FilesVO> fileList = myFilesService.getMyFiles(principal.getName());
		for (FilesVO file : fileList) {
			if (fileName.equals(file.getOutputName())) {
				try {
					BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
					FileInputStream fis = new FileInputStream(file.getOutputPath());
					int len;
					byte[] buf = new byte[1024];
					while ((len = fis.read(buf)) > 0) {
						bos.write(buf, 0, len);
					}
					bos.close();
					response.flushBuffer();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
	}
	
	@RequestMapping("/deleteFile/{fileId}")
	public String deleteFile(@PathVariable("fileId") String fileId) {
		FilesVO file = myFilesService.findFile(fileId);
		Path fileNameAndPath = Paths.get(file.getOutputPath());
		try {
			Files.deleteIfExists(fileNameAndPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		myFilesService.removeFile(fileId);
		return "redirect:/files";
	}

}
