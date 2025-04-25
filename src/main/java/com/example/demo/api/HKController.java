package com.example.demo.api;

import com.example.demo.dto.LoginVo;
import com.example.demo.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
public class HKController {
    /**
     * @param loginVo
     * @return
     */
    @PostMapping("/loginAndPlayView")
    public Result<LoginVo> loginAndPlayView(@RequestBody LoginVo loginVo) {
        return new Result<LoginVo>().ok(null);
    }
}