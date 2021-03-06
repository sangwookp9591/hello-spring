package hello.hellospring_2;

import hello.hellospring_2.repository.JdbcTemplateMemberRepository;
import hello.hellospring_2.repository.JpaMemberRepository;
import hello.hellospring_2.repository.MemberRepository;
import hello.hellospring_2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
/*@Configuration
public class SpringConfig {


    private DataSource dataSource;

    private EntityManager em;


    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean//Spring이 올라올때 얘를 spring bean contatiner에 올림
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean//Spring이 올라올때 얘를 spring bean contatiner에 올림
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
       // return new JdbcMemberRepository(dataSource);
       // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }


    //Controller는 어차피 spring이 관리하는거기 때문에 component scan으로 올라가고 autowired로 해주면됨.

}*/
