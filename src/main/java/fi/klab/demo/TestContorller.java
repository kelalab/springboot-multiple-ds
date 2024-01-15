package fi.klab.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContorller {
    
    @Autowired
    Operations operations;

    /**
     * 
     */
    @GetMapping("/addData1")
    public void addData1(@RequestParam String name){
        operations.addDataToRepo1(name);
    }

    /**
     * 
     */
    @GetMapping("/addData2")
    public void addData2(@RequestParam String name){
        operations.addDataToRepo2(name);
    }

}
