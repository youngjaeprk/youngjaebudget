package jpabook.jpashop.api;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    @PostMapping("api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    //엔티티를 파라미터로 받지 않기 위해
    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV2(){

    }

    @Data
    static class CreateMemberRequest{
        private Long id;
        
    }
    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id){
            this.id = id;
        }
    }
}
