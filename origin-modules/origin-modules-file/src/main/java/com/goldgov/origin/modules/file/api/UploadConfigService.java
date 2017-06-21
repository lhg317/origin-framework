package com.goldgov.origin.modules.file.api;

import javax.servlet.http.HttpServletRequest;

public interface UploadConfigService {

	UploadConfig getUploadConfig(HttpServletRequest request);
}
