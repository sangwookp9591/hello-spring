package hello.hellospring_2.service;

import hello.hellospring_2.domain.Member;
import hello.hellospring_2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service//스프링은 스프링 컨테이너에 스프링 빈을 등록할때 기본으로 싱글톤으로 등록한다.
public class MemberService {
    //여기서 TestCase 한번에 만드는 법 ctrl shift t
    //private final MemberRepository memberRepository =  new MemoryMemberRepository();
    //memberservice에서 얘를 지우고


    //여기 @Autowired를 쓰는 필드주입방법도 있지만 이건 별로 안좋다. 중간에 바꿀수있는 방법이하나도 없다
    private final MemberRepository memberRepository;

    //service에서 직접 new 하지 않고 외부에서 넣어준다. ->DI

    //요즘 권장하는 스타일임
    //생성자를 통해서 주입하면 처음에 application이 조립될때 주입한번되고 끝냄 -> 한번 조립되고 끝내야함
    //그다음엔 변경못하도록 막을 수가있다 생성자 setter방식도 변경이 가능함.
    /*
    setter injection방식 -> 아무개발자나 관련 메소드를 호출할수있게 열려있음.
    *  @Autowired
    public void MemberService(MemberRepository memberRepository) {
       this.memberRepository = memberRepository;
    }
    * */
    @Autowired
    public MemberService(MemberRepository memberRepository) {
       this.memberRepository = memberRepository;
    }




    /*
    * 회원가입입
   * */
    public Long join(Member member){
        //같은 이름이 있느 중복 회원 x
       /* Optional<Member> result = memberRepository.findByName(member.getName());//ctrl alt v 하면 자동으로 선언부를 리턴해줌.

        result.ifPresent(m ->
        {
            throw new IllegalStateException("이미 존재하는 회원입니다.")
        });//만약 값이 있으면*/

        //findbyName해서 로직이 이런식으로 나오는 경우는 method로 뽑는게 좋다.
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); //refactoring ctrl alert shift t
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return  memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return  memberRepository.findById(memberId);
    }
}
