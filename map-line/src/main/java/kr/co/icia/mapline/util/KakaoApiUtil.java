package kr.co.icia.mapline.util;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KakaoApiUtil {
	public static Point getPointByAddress(String address) throws IOException, InterruptedException {
		String restApiKey = "MY REST API kEY"; 
		HttpClient client = HttpClient.newHttpClient();
		String url = "https://dapi.kakao.com/v2/local/search/address.json";
		url += "?query=" + URLEncoder.encode(address, "UTF-8");
		HttpRequest request = HttpRequest.newBuilder()//
				.header("Authorization", "KakaoAK " + restApiKey)//
				.uri(URI.create(url))//
				.GET()//
				.build();

		// 요청 주문서 작성 
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String responseBody = response.body();
		System.out.println(responseBody);
		
		// 응답받은 JSON Body를 객체로 변환
		KakaoAddress kakaoAddress = new ObjectMapper().readValue(responseBody, KakaoAddress.class);
		List<KakaoAddress.Document> documents = kakaoAddress.getDocuments();
		if (documents.isEmpty()) {
			return null;
		}
		KakaoAddress.Document document = documents.get(0);
		return new Point(document.getX(), document.getY());
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class KakaoAddress {
		private List<Document> documents;

		public List<Document> getDocuments() {
			return documents;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Document {
			Double x;
			Double y;

			public Double getX() {
				return x;
			}

			public Double getY() {
				return y;
			}
		}
	}

	public static class Point {
		private Double x;
		private Double y;

		public Point(Double x, Double y) {
			this.x = x;
			this.y = y;
		}

		public Double getX() {
			return x;
		}

		public Double getY() {
			return y;
		}

	}
}