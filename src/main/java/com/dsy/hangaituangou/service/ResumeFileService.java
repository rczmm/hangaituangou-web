package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.ResumeFile;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeFileService extends IService<ResumeFile> {
    String upload(MultipartFile file);
}
