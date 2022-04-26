package com.couple.bae.controller;

import com.couple.bae.service.MemberService;
import com.couple.bae.vo.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List; import java.util.Optional;

@RestController
@RequestMapping("memberTest")
public class TestJpaRestController {
	// �⺻��
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MemberService memberService;

	// ��� ȸ�� ��ȸ
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<MemberVo>> getAllmembers() {
		List<MemberVo> member = memberService.findAll();
		return new ResponseEntity<List<MemberVo>>(member, HttpStatus.OK);
	}

	// ȸ����ȣ�� �Ѹ��� ȸ�� ��ȸ
	@GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberVo> getMember(@PathVariable("mbrNo") Long mbrNo) {
		Optional<MemberVo> member = memberService.findById(mbrNo);
		return new ResponseEntity<MemberVo>(member.get(), HttpStatus.OK);
	}

	// ȸ����ȣ�� ȸ�� ����
	@DeleteMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteMember(@PathVariable("mbrNo") Long mbrNo) {
		memberService.deleteById(mbrNo);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// ȸ����ȣ�� ȸ�� ����(mbrNo�� ȸ���� ã�� Member ��ü�� id, name�� ������)
	@PutMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberVo> updateMember(@PathVariable("mbrNo") Long mbrNo, MemberVo member) {
		memberService.updateById(mbrNo, member);
		return new ResponseEntity<MemberVo>(member, HttpStatus.OK);
	}

	// ȸ�� �Է�
	@PostMapping
	public ResponseEntity<MemberVo> save(MemberVo member) {
		return new ResponseEntity<MemberVo>(memberService.save(member), HttpStatus.OK);
	}

	// ȸ�� �Է�
	@RequestMapping(value="/saveMember", method = RequestMethod.GET)
	public ResponseEntity<MemberVo> save(HttpServletRequest req, MemberVo member){
		return new ResponseEntity<MemberVo>(memberService.save(member), HttpStatus.OK);
	}
}
