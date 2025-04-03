package com.dsy.hangaituangou.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/resume")
@RequiredArgsConstructor
@Tag(name = "简历管理", description = "简历相关的接口")
public class ResumeController {
}
