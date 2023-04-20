package com.lkhpractice.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.lkhpractice.board.dao.BDao;
import com.lkhpractice.board.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		//model안에 있는 request 객체를 빼기위해 Map형식으로 매핑 후 Map의 키값인 request로 request객체를 뺌
	
		String bid = request.getParameter("bid");
		
		BDao dao = new BDao();
		BDto dto = dao.content_view(bid);
	
		model.addAttribute("content", dto);
	}

}
