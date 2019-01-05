package com.panjura.mosque.miyabarimosque.config;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BrowserConfig {

	@Value("${application.start.url}")
	private String homepage;

	private static final String RUND1132URL = "rundll32 url.dll,FileProtocolHandler ";

	//@EventListener({ ApplicationReadyEvent.class })
	private void applicationReadyEvent() {
		browse(homepage);
	}

	private void browse(String url) {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				log.info("Error message = {}", e);
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec(RUND1132URL + url);
			} catch (IOException e) {
				log.info("Error message = {}", e);
			}
		}
	}

}
