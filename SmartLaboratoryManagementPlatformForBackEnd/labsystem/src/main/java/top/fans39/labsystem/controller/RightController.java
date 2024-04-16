package top.fans39.labsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.labsystem.pojo.Rights;
import top.fans39.labsystem.service.RightService;
import top.fans39.labsystem.utils.resultOBJ;

import java.util.List;

@RestController
@RequestMapping("/adminapi/rights")
public class RightController {
    @Autowired
    private RightService rightService;

    @GetMapping
    public List<Rights> getRightList(){
        return rightService.getRightList();
    }

    @PutMapping(value = "/{id}")
    public resultOBJ updateRightList(@PathVariable Integer id, @RequestBody Rights rights){
        try {
            rights.setId(id);
            rightService.updateRightList(rights);
            return resultOBJ.UPDATE_ERROR;
        } catch (Exception e) {
            return resultOBJ.UPDATE_ERROR;
        }
    }


    @DeleteMapping(value = "{id}")
    public resultOBJ deleteRightList(@PathVariable Integer id){

        try {
            rightService.deleteRightList(id);
            return resultOBJ.DELETE_SUCCESS;
        } catch (Exception e) {
            return resultOBJ.DELETE_ERROR;
        }
    }
}
