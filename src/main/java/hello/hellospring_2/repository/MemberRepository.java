package hello.hellospring_2.repository;

import hello.hellospring_2.domain.Member;

import java.util.List;
import java.util.Optional;
//데이터 저장소
public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); //java 8기능 없으면 null 이 반환하는데 Optional로 감싸서 반환하는것을 선호
    Optional<Member> findByName(String name);
    List<Member> findAll();




}
