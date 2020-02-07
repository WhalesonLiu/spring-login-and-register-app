package com.kgregorczyk.library.web.controller;

import com.kgregorczyk.library.enums.ResultEnum;
import com.kgregorczyk.library.message.Message;
import com.kgregorczyk.library.model.NCoV;
import com.kgregorczyk.library.service.NCoVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/2019-nCoV")
public class NCoVController {

    private Message message = new Message();

    private final NCoVService nciService;

    @Autowired
    public NCoVController(NCoVService nciService){

        this.nciService = nciService;
    }
    @PostMapping("")
    public ResponseEntity<Message> addCases(@RequestBody NCoV NCoV){
        System.out.println(NCoV);
        NCoV = nciService.saveNCICase(NCoV);
        if (NCoV == null){

            message.setMsg(ResultEnum.ADD_NCI_FAILD);

        }
        message.setMsg(ResultEnum.ADD_NCI_SUCCESS);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    /*https://www.cnblogs.com/yfzhou/p/9661994.html*/
    @GetMapping("/list")
    public ResponseEntity<Message> getNCIList(@RequestParam(value = "conditions[]") List<String> conditions){

        List<Map<String,Object>> nCoVList = nciService.getAll2019_nCoVList(conditions);

        if(nCoVList != null && !nCoVList.isEmpty()){

            message.setMsg(ResultEnum.QUERY_SECCESS, nCoVList);

        }else {

            message.setMsg(ResultEnum.QUERY_EMPTY);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
