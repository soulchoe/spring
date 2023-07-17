package com.bs.spring.board.model.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.bs.spring.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@NotNull
	private int boardNo;
	private String boardTitle;
	private Member boardWriter;
	private String boardContent;
	@PastOrPresent
	private Date boardDate;
	private int boardReadCount;
	
}
