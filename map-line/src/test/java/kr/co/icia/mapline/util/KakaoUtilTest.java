package kr.co.icia.mapline.util;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import kr.co.icia.mapline.util.KakaoApiUtil.Point;

public class KakaoUtilTest {

	@Test
	public void getPointByAddressTest() throws IOException, InterruptedException {
		Point point = KakaoApiUtil.getPointByAddress("인천광역시 미추홀구 매소홀로488번길 6-32 태승빌딩 5층");
		System.out.println("x:" + point.getX() + ",y:" + point.getY());
	}
}