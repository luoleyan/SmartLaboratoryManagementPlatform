package top.fans39.labsystem.service;

import top.fans39.labsystem.pojo.Labs;

import java.util.List;

public interface LabsService  {
    public List<Labs> getLabList(Labs labs);

    public void addLabList(Labs labs);

}
