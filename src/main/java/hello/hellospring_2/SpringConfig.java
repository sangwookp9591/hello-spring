package hello.hellospring_2;

import hello.hellospring_2.repository.MemberRepository;
import hello.hellospring_2.repository.MemoryMemberRepository;
import hello.hellospring_2.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean//Spring이 올라올때 얘를 spring bean contatiner에 올림
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean//Spring이 올라올때 얘를 spring bean contatiner에 올림
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();

    }


    //Controller는 어차피 spring이 관리하는거기 때문에 component scan으로 올라가고 autowired로 해주면됨.

}
