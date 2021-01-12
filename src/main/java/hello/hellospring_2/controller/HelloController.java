package hello.hellospring_2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name",required = true) String name, Model model){
        model.addAttribute("name",name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //html에 나오는 body가아니라 http의 응답 body부에 return값을 직접 넣어주겠다는 얘기
    public String helloString(@RequestParam("name") String name){
        return "hello"+name; //"hello spring"이라고 했을때 이문자가 요청한 클라이언트에 그냥 내려감
        // view없이 그냥내려감.(소스보기하면 html태그없이 그냥 내려감. )
    }

    @GetMapping("hello-api")
    @ResponseBody //spring 자체에서 객체로 반환하고  resopnsebody로 반환하면 json으로 return하는게 default임.
    public Hello hellApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
