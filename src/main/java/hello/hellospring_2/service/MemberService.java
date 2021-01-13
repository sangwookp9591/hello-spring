package hello.hellospring_2.service;

import hello.hellospring_2.domain.Member;
import hello.hellospring_2.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //여기서 TestCase 한번에 만드는 법 ctrl shift t
    //private final MemberRepository memberRepository =  new MemoryMemberRepository();
    //memberservice에서 얘를 지우고
    private final MemberRepository memberRepository;

    //service에서 직접 new 하지 않고 외부에서 넣어준다. ->DI
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
