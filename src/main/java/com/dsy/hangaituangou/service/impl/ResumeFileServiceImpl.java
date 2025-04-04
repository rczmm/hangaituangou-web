package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.ResumeFile;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.mapper.ResumeFileMapper;
import com.dsy.hangaituangou.service.ResumeFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class ResumeFileServiceImpl extends ServiceImpl<ResumeFileMapper, ResumeFile> implements ResumeFileService {

    @Value("${file.upload.resume-path}")
    private String uploadResumePath;

    @Override
    public String upload(MultipartFile file) {

        if (file.isEmpty()) {
            throw new BusinessException("传入文件为空！");
        }

        // 校验文件类型
        String contentType = file.getContentType();

        if (contentType == null || (!contentType.equals("application/pdf") && !contentType.equals("application/msword")
                && !contentType.equals("image/png")
        )) {
            throw new IllegalArgumentException("不支持的文件类型: " + contentType);
        }

        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = "";

        int lastDotIndex = originalFilename.lastIndexOf(".");
        if (lastDotIndex > 0) {
            fileExtension = originalFilename.substring(lastDotIndex); // 获取文件扩展名 (如 .pdf)
        }
        // 使用 UUID 生成唯一文件名，保留原始扩展名
        String uniqueFileName = UUID.randomUUID() + fileExtension;
        // 拼接最终存储路径 (确保基础目录存在)
        Path destinationDirectory = Paths.get(uploadResumePath);
        try {
            // 如果目录不存在，尝试创建 (需要应用有权限)
            Files.createDirectories(destinationDirectory);
            Path destinationFile = destinationDirectory.resolve(Paths.get(uniqueFileName)).normalize().toAbsolutePath();

            // 防止目录遍历攻击 (虽然resolve().normalize()已处理，多一层检查更安全)
            if (!destinationFile.getParent().equals(destinationDirectory.toAbsolutePath())) {
                throw new IllegalArgumentException("无法存储文件到指定路径之外");
            }

            // --- 3. 存储文件到服务器 ---
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println("存储文件失败: " + e.getMessage());
                throw new IOException("存储文件失败", e); // 重新抛出，触发事务回滚
            }

        } catch (Exception e) {
            throw new BusinessException("文件上传失败！{}" + e.getMessage());
        }
        return uniqueFileName;
    }
}
