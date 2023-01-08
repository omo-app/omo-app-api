package com.findev.omo.controller;


import com.findev.omo.controller.dto.SignUpRequest;
import com.findev.omo.controller.dto.MemberDto;
import com.findev.omo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberController {
  private final MemberService memberService;

  @PostMapping("")
  public ResponseEntity<MemberDto> signUp(@RequestBody SignUpRequest req){
    MemberDto res = memberService.signUp(req);

    return ResponseEntity.ok(res);
  }
}
