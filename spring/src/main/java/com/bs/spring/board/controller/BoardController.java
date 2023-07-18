package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;
@RequestMapping("/board")
@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/boardList.do")
	public String selectBoardList(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage ,Model m) {
		List<Board> list=service.selectBoardList(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectBoardCount();
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "boardList.do"));
		// /spring/board/baordList.do로 상대경로로 써줘도 됨
		m.addAttribute("totalData",totalData);
		m.addAttribute("list",list);
		
		return "board/boardList";
	}
	
	@RequestMapping("/boardDetail.do")
	public String boardDetail(int boardNo, Model m) {
		Board b=service.boardDetailByNo(boardNo);
		m.addAttribute("b",b);
		return "board/boardDetail";//보드번호를 매개변수로 받아와야해
	}
	@RequestMapping("/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b, MultipartFile[] upFile, HttpSession session, Model m) {
		log.info("{}",b);
		log.info("{}",upFile);
		
		//절대경로 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		
		//MultipartFile에서 제공하는 메소드룰 이용해서 파일을 저장할 수 있음
		//:transferTo()메소드를 이용
		//파일명에 대한 rename규칙을 설정해줘야함
		//직접 rename규칙을 만들어서 저장해보자
		//yyyyMMdd_HHmmssSSS_랜덤값
//		List<Attachment> files=new ArrayList();
		if(upFile != null) {
			for(MultipartFile mf : upFile) {
				if(!mf.isEmpty()) {
					String oriName = mf.getOriginalFilename();// 확장자를 가져오기 위해 원본이름이 필요함
					String ext = oriName.substring(oriName.lastIndexOf("."));
					Date today = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn = (int) (Math.random() * 10000) + 1;
					String rename = sdf.format(today) + "_" + rdn + ext;

					try {
						mf.transferTo(new File(path + rename));
					} catch (IOException e) {
						e.printStackTrace();
					}

					Attachment file = Attachment.builder().originalFilename(oriName).renamedFilename(rename).build();
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);
		}catch(RuntimeException e) {
			for(Attachment a:b.getFile()){
				//등록에 실패한 파일은 지워줘야지
				File delFile=new File(path+a.getRenamedFilename());
				delFile.delete();
			}
			m.addAttribute("msg","글쓰기 등록 실패 ;P");
			m.addAttribute("loc","/board/boardForm.do");
			return "common/msg";
		}
		
		return "redirect:/board/boardList.do";
	}
	@RequestMapping("/fileDownload")
	public void fileDown(String oriname, String rename, OutputStream out, 
			@RequestHeader(value="user-agent")String header,
			HttpSession session, HttpServletResponse res) {
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+rename);
		try (FileInputStream fis=new FileInputStream(downloadFile);
				BufferedInputStream bis=new BufferedInputStream(fis);
				BufferedOutputStream bos=new BufferedOutputStream(out)){
			boolean isMS=header.contains("Trident")||header.contains("MSIE");
			String encodeRename="";
			if(isMS) {
				encodeRename=URLEncoder.encode(oriname,"utf-8");
				encodeRename=encodeRename.replaceAll("\\+","%20");
			}else {
				encodeRename=new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition","attachment;filename=\""+encodeRename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				bos.write(read);
			}
				
				
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
