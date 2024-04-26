package top.fans39.labsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.labsystem.pojo.Labs;
import top.fans39.labsystem.service.LabsService;
import top.fans39.labsystem.utils.resultOBJ;

import java.util.List;

@RestController
@RequestMapping("/adminapi/labs")
public class LabsController {
    @Autowired
    private LabsService labsService;

    @GetMapping
    public List<Labs> getLabList(Labs labs){
        return labsService.getLabList(labs);
    }

    @PostMapping
    public resultOBJ addLabList(@RequestBody Labs labs){
        try {
            labsService.addLabList(labs);
            return resultOBJ.ADD_SUCCESS;
        } catch (Exception e) {
            return resultOBJ.ADD_ERROR;
        }
    }
}
