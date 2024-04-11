package top.fans39.labsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.labsystem.pojo.Right;
import top.fans39.labsystem.service.RightService;

import java.util.List;

@RestController
@RequestMapping("/adminapi/rights")
public class RightController {
    @Autowired
    private RightService rightService;

    @GetMapping
    public List<Right> getRightList(){
        return rightService.getRightList();
    }

    @PutMapping(value = "/{id}")
    public String updateRightList(@PathVariable Integer id, @RequestBody Right right){
        right.setId(id);
        rightService.updateRightList(right);
        return "success";
    }
}
