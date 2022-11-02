package com.barclays.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.barclays.exception.PaymentsException;

public interface ExportToCsvService {

	void listall(HttpServletResponse response) throws IOException, PaymentsException;

}
