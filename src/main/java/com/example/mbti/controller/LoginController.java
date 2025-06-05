import com.example.mbti.domain.Member;
import com.example.mbti.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "errorMessage", required = false) String errorMessage,
                            Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String userid,
                        @RequestParam String userpw,
                        RedirectAttributes redirectAttributes) {

        Member member = memberService.findByUserid(userid);

        if (member == null) {
            redirectAttributes.addAttribute("errorMessage", "아이디가 존재하지 않습니다.");
            return "redirect:/login";
        }

        if (!member.getUserpw().equals(userpw)) {
            redirectAttributes.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "redirect:/login";
        }

        // 로그인 성공 시 세션 저장 등 처리
        // 예: session.setAttribute("loginUser", member);
        return "redirect:/"; // 로그인 후 메인 페이지 등으로 이동
    }
}
