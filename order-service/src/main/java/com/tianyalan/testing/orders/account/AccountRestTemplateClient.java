package com.tianyalan.testing.orders.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class AccountRestTemplateClient {

	private final String serviceHost;

	private final RestTemplate restTemplate;

	@Autowired
	public AccountRestTemplateClient(RestTemplate restTemplate, @Value("${user-service.host:user-service}") String sh) {
		this.serviceHost = sh;
		this.restTemplate = restTemplate;
	}

	public Account getAuthenticatedAccount() {
		URI url = URI.create(String.format("http://%s/account/v1/tianyalan", serviceHost));
		RequestEntity<Void> request = RequestEntity.get(url).header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.build();
		return restTemplate.exchange(request, Account.class).getBody();
	}
}
