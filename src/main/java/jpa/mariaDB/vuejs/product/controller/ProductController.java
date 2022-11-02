package jpa.mariaDB.vuejs.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jpa.mariaDB.vuejs.product.service.ProductService;
import jpa.mariaDB.vuejs.product.vo.Product;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin("http://localhost:8040/")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping(value="/product")
	public List<Product> fetchMember() {
		return service.listAll();
	}
	
	// index : List 가져오기
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Map<String, Object> viewHome(Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();

//		String resultCode = "400";
//		String resultMsg = "fail";
		
		try {
			List<Product> list = service.listAll();
//			if(list.size() > 0) {
//				resultCode = "200";
//				resultMsg = "success";
//			}
			map.put("list", list);
		
		}catch(Exception e) {
//			resultCode = "400";
//			resultMsg = e.getMessage();
//			e.printStackTrace();
		}
//		map.put("resultCode", resultCode);
//		map.put("resultMsg", resultMsg);
		
		System.out.println(map);
		
		return map;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductDetail(@RequestParam String id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String resultMsg = "상품 상세 조회에 실패했습니다.";
		String result = "fail";
		Product resultVo = null;
		
		try {
			if(id != null) {
				resultVo = service.get(Integer.parseInt(id));
				resultMsg = "상품 상세 조회에 성공했습니다.";
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("result", result);
		map.put("detail", resultVo);
		map.put("resultMsg", resultMsg);
		System.out.println("response data: " + map);
		return map;
	}
	
//	@RequestMapping("/")
//	public String viewHome(Model model) {
//		
//		List<Member> list = service.listAll();
//		model.addAttribute("list",list);
//		
//		return "index";   // templates/index.html
//	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> regProduct(@RequestBody Product param, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String resultMsg = "상품 등록에 실패했습니다.";
		String result = "fail";
		Product resultVo = null;
		
		try {
			resultVo = service.save(param);
			if(resultVo.getId() != 0) {
				resultMsg = "상품 등록에 성공하였습니다.";
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("result", result);
		map.put("resultId", resultVo.getId());
		map.put("resultMsg", resultMsg);
		return map;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProduct(@RequestBody Product param, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String resultMsg = "상품 수정에 실패했습니다.";
		String result = "fail";
		Product resultVo = null;
		
		try {
			resultVo = service.save(param);
			if(resultVo.getId() != 0) {
				resultMsg = "상품 수정에 성공하였습니다.";
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("result", result);
		map.put("resultId", resultVo.getId());
		map.put("resultMsg", resultMsg);
		return map;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteProduct(@RequestBody HashMap<String, String> param, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String resultMsg = "상품 삭제에 실패했습니다.";
		String result = "fail";
		try {
			if(param.get("id") != null) {
				service.delete(Integer.parseInt(param.get("id")));
				resultMsg = "상품 삭제에 성공했습니다.";
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("result", result);
		map.put("resultMsg", resultMsg);
		return map;
	}
}
