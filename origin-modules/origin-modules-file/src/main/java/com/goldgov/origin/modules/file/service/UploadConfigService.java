package com.goldgov.origin.modules.file.service;

import javax.servlet.http.HttpServletRequest;

public interface UploadConfigService {

	UploadConfig getUploadConfig(HttpServletRequest request);
}
