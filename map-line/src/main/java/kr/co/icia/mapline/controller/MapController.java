package kr.co.icia.mapline.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.icia.mapline.util.KakaoApiUtil;
import kr.co.icia.mapline.util.KakaoApiUtil.Point;

@Controller
public class MapController {

	/**
	 * 주소를 좌표로 변환
	 * 
	 * @param address 주소정보
	 * @param model   html파일에 값을 전달해주는 객체
	 * @return html 파일위치
	 * 
	 */
	@GetMapping("/map/address/point") // url : /map/address/point
	public String getMapAddressPoint(@RequestParam(required = false) String address, Model model)
			throws IOException, InterruptedException {
		if (address != null && !address.isEmpty()) {
			Point point = KakaoApiUtil.getPointByAddress(address);
			model.addAttribute("point", point);
		}
		return "map/address_point";
	}

}