package com.example.oven.program.service;
import com.example.oven.program.Baking;
import com.example.oven.program.WarmUp;
import com.example.oven.program.Oven;
import com.example.oven.program.Program;
import com.example.oven.service.BakingService;
import com.example.oven.service.OvenService;
import com.example.oven.service.ProgramService;
import com.example.oven.service.WarmUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static com.example.oven.util.OvenMode.*;

@Service
public class Initialize {
    private final BakingService bakingService;
    private final WarmUpService warmUpService;
    private final OvenService ovenService;
    private final ProgramService programService;

    private Oven oven;

    @Autowired
    public Initialize(BakingService bakingService, WarmUpService warmUpService, OvenService ovenService, ProgramService programService) {
        this.bakingService = bakingService;
        this.warmUpService = warmUpService;
        this.ovenService = ovenService;
        this.programService = programService;
    }

    @PostConstruct
    public void init(){
        oven = ovenService.getFirst();
        if(oven == null){
            oven = initOven();
        }
    }

    public Oven getOven() {
        return oven;
    }
    private Oven initOven(){
    Oven ov = new Oven();
    ov.setModel("EBATYAUSTAL");
    ov.setSerial("1488NHY");
    ov.setPrograms(Arrays.asList(
            programService.save(grillPorkProgram()),
            programService.save(veggieProgram()),
            programService.save(bakeCakeProgram())
        ));
    return ov;

    }

    private Program grillPorkProgram(){
        Program grillPork = new Program();
        grillPork.setWarmUp(warmUpService.save(new WarmUp(10000L,200,GRILL)));
        grillPork.setBaking(bakingService.save(new Baking(300000L,200,GRILL)));

        return grillPork;
    }
    private Program veggieProgram(){
        Program veggie = new Program();
        veggie.setWarmUp(warmUpService.save(new WarmUp(2000L,80,BOTTOM)));
        veggie.setBaking(bakingService.save(new Baking(6000L,80,BOTTOM)));

        return veggie;
    }
    private Program bakeCakeProgram(){
        Program bakeCake = new Program();
        bakeCake.setWarmUp(warmUpService.save(new WarmUp(6000L,120,CONVENTIONAL)));
        bakeCake.setBaking(bakingService.save(new Baking(12000L,120,CONVENTIONAL)));

        return bakeCake;
    }


}
