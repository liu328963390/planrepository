package com.plan.respository.usermanagement.word.control;

import com.plan.respository.usermanagement.word.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WordController {

    @Autowired
    private WordService wordService;

    @RequestMapping
    @ResponseBody
    public String readword(String path){
        return wordService.readWord(path);
    }


}
