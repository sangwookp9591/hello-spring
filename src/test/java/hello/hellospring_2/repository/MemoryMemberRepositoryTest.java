package hello.hellospring_2.repository;

import hello.hellospring_2.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =  new MemoryMemberRepository();

    //모든 테스트의 순서는 보장이 되지않는다. 순서랑 상관없이 돌도록 설계해야한다. 순서에 의존하면안된다.
    //테스트가 끝나면 깔끔하게  repository를 clear 시켜야한다.

    @AfterEach
    public void afterEash(){
        repository.clearStore(); //테스트가끝날때마다 repository를 지우니깐 문제가 생기지 않는다.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result =  repository.findById(member.getId()).get();

        //Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);


        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        List<Member> result =  repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }




}
