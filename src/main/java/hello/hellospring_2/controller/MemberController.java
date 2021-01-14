package hello.hellospring_2.controller;

import hello.hellospring_2.domain.Member;
import hello.hellospring_2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    private final MemberService memberService;

    @Autowired //spring container에서 memberService를 가져옴.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    //이렇게되면 Memberservice를 여러군대서 사용하게됨.
    //여러개를 생성할필요강벗고 하나만생성하고 공용으로 사용해야함.
    //spring contatiner에 등록하면 딱 하나만 등록됨.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members =  memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
