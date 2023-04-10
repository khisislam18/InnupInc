package com.innup.startupinvest.services;

import com.innup.startupinvest.models.StartUp;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StartUpService {
    private List<StartUp> startUpList = new ArrayList<>();
    private long ID = 0;
    {
        startUpList.add(new StartUp(++ID,"PlayStation", "null", 10000, "Islam",true, LocalDate.now()));
        startUpList.add(new StartUp(++ID,"X-BOX","null",10000,"Egor",false,LocalDate.now()));
    }

    public List<StartUp> list() {
        return startUpList;
    }
    public void saveStartUp(StartUp startUp){
        startUp.setID(++ID);
        startUp.setCreationDate(LocalDate.now());
        startUpList.add(startUp);
    }
    public void deleteStartUp(Long id){
        startUpList.removeIf(startUp -> startUp.getID().equals(id));
    }

    public StartUp getStartupById(Long id) {
        for (StartUp startUp: startUpList) {
            if(startUp.getID().equals(id))
                return startUp;
        }
        return null;
    }
}
