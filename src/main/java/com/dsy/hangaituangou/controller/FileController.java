package com.dsy.hangaituangou.controller;

import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.ResumeFileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("file")
@RequiredArgsConstructor
@Tag(name = "文件管理", description = "文件管理")
public class FileController {

    private final ResumeFileService resumeFileService;


    @PostMapping("upload")
    public RespBase<String> uploadResume(@RequestParam("file") MultipartFile file){
        return RespBase.success(resumeFileService.upload(file));
    }

}
